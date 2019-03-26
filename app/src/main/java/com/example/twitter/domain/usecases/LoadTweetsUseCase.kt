package com.example.twitter.domain.usecases

import androidx.annotation.CheckResult
import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.dto.Tweet
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoadTweetsUseCase @Inject constructor(
	private val api: RestApi
) {
	@CheckResult
	fun loadTweets(): Single<ApiResponse<List<Tweet>>> {
		return api.getTweets()
			// todo inject
			.subscribeOn(Schedulers.io())
			// todo inject
			.observeOn(AndroidSchedulers.mainThread())
	}
}
