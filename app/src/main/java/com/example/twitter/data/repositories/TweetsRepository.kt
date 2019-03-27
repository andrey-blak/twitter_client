package com.example.twitter.data.repositories

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.database.Tweet_
import com.example.twitter.data.database.tweetBox
import com.example.twitter.data.dto.Response
import com.example.twitter.data.dto.Tweet
import com.example.twitter.data.mappers.TweetMapper
import io.objectbox.BoxStore
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TweetsRepository @Inject constructor(
	private val api: RestApi,
	private val boxStore: BoxStore
) {
	fun observeTweets(): Observable<out List<com.example.twitter.data.database.Tweet>> {
		return getTweetsFromDatabase()
	}

	fun loadTweets(): Observable<Response<Any?>> {
		return loadTweetsFromNetwork()
	}

	fun addTweet(username: String, timeMs: Long, message: String): Single<Response<com.example.twitter.data.database.Tweet>> {
		return api.postTweet(username, timeMs, message)
			.observeOn(Schedulers.io())
			.map { apiResponse ->
				when (apiResponse) {
					is ApiResponse.Success -> {
						val tweet = saveToDatabase(apiResponse.data)
						Response.Success(tweet)
					}
					is ApiResponse.Error -> {
						Response.Error<com.example.twitter.data.database.Tweet>()
					}
				}
			}
	}

	private fun loadTweetsFromNetwork(): Observable<Response<Any?>> {
		return api.getTweets()
			.observeOn(Schedulers.io())
			.flatMapObservable { apiResponse ->
				val response: Response<Any?> = when (apiResponse) {
					is ApiResponse.Success -> {
						val tweets = apiResponse.data
						saveToDatabase(tweets)
						Response.Success(null)
					}
					is ApiResponse.Error -> {
						Response.Error()
					}
				}
				return@flatMapObservable Observable.just(response)
			}
	}

	private fun getTweetsFromDatabase(): Observable<List<com.example.twitter.data.database.Tweet>> {
		val tweetBox = boxStore.tweetBox()
		val query = tweetBox.query()
			.build()
		return RxQuery.observable(query)
	}

	private fun saveToDatabase(apiTweets: List<Tweet>) {
		val tweets = apiTweets.map(TweetMapper())
		val tweetBox = boxStore.tweetBox()
		boxStore.runInTx {
			for (tweet in tweets) {
				val dbTweet = tweetBox.query()
					.equal(Tweet_.uid, tweet.uid)
					.build()
					.findUnique()
				if (dbTweet == null) {
					tweetBox.put(tweet)
				}
			}
		}
	}

	private fun saveToDatabase(apiTweet: Tweet): com.example.twitter.data.database.Tweet {
		val tweetBox = boxStore.tweetBox()
		val tweet = TweetMapper().invoke(apiTweet)
		tweetBox.put(tweet)
		return tweet
	}
}
