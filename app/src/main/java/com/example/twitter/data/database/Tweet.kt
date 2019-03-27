package com.example.twitter.data.database

import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Unique
import io.objectbox.kotlin.boxFor

@Entity
data class Tweet(
	@Id
	var id: Long = ObjectBox.NEW_ID,
	@Unique
	val uid: String,
	val username: String,
	val postedTimeMs: Long,
	val message: String
)

fun BoxStore.tweetBox(): Box<Tweet> {
	return boxFor()
}
