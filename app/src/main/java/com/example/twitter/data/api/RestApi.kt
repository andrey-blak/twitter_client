package com.example.twitter.data.api

import io.reactivex.Single

interface RestApi {
	fun login(username: String, password: String): Single<ApiResponse<Void>>
}
