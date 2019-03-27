package com.example.twitter.domain.usecases

import com.example.twitter.data.database.Tweet
import com.example.twitter.data.dto.Response
import com.example.twitter.data.repositories.TweetsRepository
import com.example.twitter.data.repositories.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SendTweetUseCase @Inject constructor(
	private val tweetsRepository: TweetsRepository,
	private val userRepository: UserRepository
) {
	fun send(message: String): Single<Response<Tweet>> {
		return userRepository.getUsername()
			.flatMap { username ->
				// todo ideally, time should be obtained from an injected repository for better testability
				val timeMs = System.currentTimeMillis()

				tweetsRepository.addTweet(username, timeMs, message)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
			}
	}
}
