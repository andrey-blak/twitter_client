package com.example.twitter.data.repositories

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.database.Tweet_
import com.example.twitter.data.database.tweetBox
import com.example.twitter.data.dto.Response
import com.example.twitter.data.dto.Tweet
import com.example.twitter.data.mappers.TweetMapper
import io.objectbox.BoxStore
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TweetsRepository @Inject constructor(
	private val api: RestApi,
	private val boxStore: BoxStore
) {
	fun getTweets(): Observable<Response<out List<com.example.twitter.data.database.Tweet>>> {
		return Observable.mergeArray(
			loadTweetsFromNetwork(),
			getTweetsFromDatabase()
		)
	}

	private fun loadTweetsFromNetwork(): Observable<Response<out List<com.example.twitter.data.database.Tweet>>> {
		return api.getTweets()
			.observeOn(Schedulers.io())
			.flatMapObservable { apiResponse ->
				when (apiResponse) {
					is ApiResponse.Success -> {
						val tweets = apiResponse.data
						saveToDatabase(tweets)
						getTweetsFromDatabase()
					}
					is ApiResponse.Error -> {
						Observable.just(Response.Error<List<com.example.twitter.data.database.Tweet>>())
					}
				}
			}
	}

	private fun getTweetsFromDatabase(): Observable<Response.Success<out List<com.example.twitter.data.database.Tweet>>> {
		return Observable.fromCallable {
			val tweetBox = boxStore.tweetBox()
			val tweets = tweetBox.all
			Response.Success(tweets)
		}
	}

	private fun saveToDatabase(apiTweets: List<Tweet>) {
		val tweets = apiTweets.map(TweetMapper())
		val tweetBox = boxStore.tweetBox()
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
