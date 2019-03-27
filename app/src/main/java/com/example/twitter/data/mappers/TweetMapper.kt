package com.example.twitter.data.mappers

import com.example.twitter.data.dto.Tweet

class TweetMapper : (Tweet) -> com.example.twitter.data.database.Tweet {
	override fun invoke(apiTweet: Tweet): com.example.twitter.data.database.Tweet {
		return com.example.twitter.data.database.Tweet(
			uid = apiTweet.uid,
			username = apiTweet.username,
			message = apiTweet.message,
			postedTimeMs = apiTweet.postedTimeMs
		)
	}
}
