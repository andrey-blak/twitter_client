package com.example.twitter.data.dto

sealed class Response<T> {
	class Success<T>(
		val data: T
	) : Response<T>()

	class Error<T> : Response<T>()
}
