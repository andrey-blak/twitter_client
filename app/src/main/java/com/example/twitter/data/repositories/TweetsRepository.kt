package com.example.twitter.data.repositories

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.dto.Response
import com.example.twitter.data.dto.Tweet
import io.reactivex.Observable
import javax.inject.Inject

class TweetsRepository @Inject constructor(
	private val api: RestApi
) {
	fun getTweets(): Observable<Response<List<Tweet>>> {
		return api.getTweets()
			.map { apiResponse ->
				when (apiResponse) {
					is ApiResponse.Success -> Response.Success(apiResponse.data)
					is ApiResponse.Error -> Response.Error<List<Tweet>>()
				}
			}
			.toObservable()
	}
}
