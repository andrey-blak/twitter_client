package com.example.twitter.data.dto

data class Tweet(
	val uid: String,
	val username: String,
	val postedTimeMs: Long,
	val message: String
)
