package com.example.twitter.domain.usecases

import com.example.twitter.data.database.Tweet
import com.example.twitter.data.repositories.TweetsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ObserveTweetsUseCase @Inject constructor(
	private val tweetsRepository: TweetsRepository
) {
	fun observeTweets(): Observable<out List<Tweet>> {
		return tweetsRepository.observeTweets()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
	}
}
