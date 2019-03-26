package com.example.twitter.data.api

sealed class ApiResponse<T> {
	class Success<T>(
		val data: T
	) : ApiResponse<T>()

	class Error<T> : ApiResponse<T>()
}
