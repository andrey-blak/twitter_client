package com.example.twitter.data.api

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockRestApi : RestApi {
	override fun login(username: String, password: String): Single<ApiResponse<Void>> {
		return Single.fromCallable {
			if (username == USERNAME && password == PASSWORD) {
				ApiResponse.Success<Void>()
			} else {
				ApiResponse.Error<Void>()
			}
		}
			.delay(DELAY_MS, TimeUnit.MILLISECONDS)
	}

	companion object {
		private const val USERNAME = "user"
		private const val PASSWORD = "pass"
		private const val DELAY_MS: Long = 1000
	}
}
