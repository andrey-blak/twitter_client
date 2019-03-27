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
	fun getTweets(): Observable<Response<List<Tweet>>> {
		return api.getTweets()
			.observeOn(Schedulers.io())
			.map { apiResponse ->
				when (apiResponse) {
					is ApiResponse.Success -> {
						val tweets = apiResponse.data
						saveToDatabase(tweets)
						Response.Success(tweets)
					}
					is ApiResponse.Error -> Response.Error<List<Tweet>>()
				}
			}
			.toObservable()
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
