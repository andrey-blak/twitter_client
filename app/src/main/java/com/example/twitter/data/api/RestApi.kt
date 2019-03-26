package com.example.twitter.data.api

import com.example.twitter.data.dto.Tweet
import io.reactivex.Single

interface RestApi {
	fun login(username: String, password: String): Single<ApiResponse<out Any?>>
	fun getTweets(): Single<ApiResponse<List<Tweet>>>
}
