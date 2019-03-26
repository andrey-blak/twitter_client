package com.example.twitter.domain.usecases

import androidx.annotation.CheckResult
import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(
	private val api: RestApi
) {
	@CheckResult
	public fun login(username: String, password: String): Single<ApiResponse<Void>> {
		return api.login(username, password)
			// todo inject
			.subscribeOn(Schedulers.io())
			// todo inject
			.observeOn(AndroidSchedulers.mainThread())
	}
}
