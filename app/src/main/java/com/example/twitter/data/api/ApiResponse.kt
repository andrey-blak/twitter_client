package com.example.twitter.data.api

sealed class ApiResponse<T> {
	class Success<T> : ApiResponse<T>()
	class Error<T> : ApiResponse<T>()
}
