package com.example.twitter.domain.usecases

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.dto.Tweet
import com.example.twitter.data.repositories.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SendTweetUseCase @Inject constructor(
	private val api: RestApi,
	private val userRepository: UserRepository
) {
	fun send(message: String): Single<out ApiResponse<Tweet>> {
		return userRepository.getUsername()
			.flatMap { username ->
				// todo ideally, time should be obtained from an injected repository for better testability
				val timeMs = System.currentTimeMillis()

				val tweet = Tweet(username, timeMs, message)
				api.postTweet(tweet)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
			}
	}
}
