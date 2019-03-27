package com.example.twitter.data.api

import com.example.twitter.data.dto.Tweet
import com.example.twitter.data.dto.User
import io.reactivex.Single

interface RestApi {
	fun login(username: String, password: String): Single<ApiResponse<User>>
	fun getTweets(): Single<ApiResponse<List<Tweet>>>
	fun postTweet(username: String, timeMs: Long, message: String): Single<ApiResponse<Tweet>>
}
