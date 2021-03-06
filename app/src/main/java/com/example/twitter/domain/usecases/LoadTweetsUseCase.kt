package com.example.twitter.domain.usecases

import androidx.annotation.CheckResult
import com.example.twitter.data.dto.Response
import com.example.twitter.data.repositories.TweetsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoadTweetsUseCase @Inject constructor(
	private val tweetsRepository: TweetsRepository
) {
	@CheckResult
	fun loadTweets(): Observable<Response<Any?>> {
		return tweetsRepository.loadTweets()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
	}
}
