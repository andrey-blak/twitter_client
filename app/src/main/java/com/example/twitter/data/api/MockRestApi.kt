package com.example.twitter.data.api

import com.example.twitter.data.dto.Tweet
import com.example.twitter.data.dto.User
import io.reactivex.Single
import java.util.UUID
import java.util.concurrent.TimeUnit

class MockRestApi : RestApi {
	private val tweets = mutableListOf(
		Tweet("1", "Grace Merrill", 1553186389000, "mollit in do ea proident sunt excepteur officia laboris ipsum cupidatat ullamco labore duis exercitation proident"),
		Tweet("2", "Raquel Frost", 1402741018710, "sunt ea minim tempor excepteur ut Lorem nisi magna reprehenderit occaecat proident veniam elit nisi mollit"),
		Tweet("3", "Carol Clay", 1473686390161, "proident sunt non occaecat sit cupidatat quis id cupidatat minim aliqua aute amet anim fugiat enim"),
		Tweet("4", "Sexton Whitfield", 1435376998106, "sit aliqua do in dolore culpa eu eu non labore esse deserunt consequat mollit ea magna"),
		Tweet("5", "Hendricks Stevens", 1391267804940, "do labore laborum adipisicing irure id deserunt commodo non aliqua enim non sunt ex laborum ut"),
		Tweet("6", "Wallace Rivera", 1515572847338, "velit cillum do culpa consectetur magna consequat dolor amet nostrud minim sint ullamco sunt est exercitation"),
		Tweet("7", "Gabrielle Mosley", 1513495118524, "ut commodo dolore deserunt pariatur minim veniam aliquip do minim deserunt magna culpa et occaecat aliqua"),
		Tweet("8", "Joann Carpenter", 1422466644070, "reprehenderit ut ipsum non cillum laborum dolore pariatur commodo aliqua culpa excepteur dolor dolore laborum eiusmod"),
		Tweet("9", "Ola Trevino", 1478898255586, "ex deserunt ad consequat cupidatat labore amet amet veniam culpa esse ullamco exercitation culpa sunt sunt"),
		Tweet("10", "Ford Rice", 1456090019926, "tempor adipisicing ut ut enim laboris incididunt occaecat velit est adipisicing duis cupidatat nostrud quis adipisicing")
	)

	override fun login(username: String, password: String): Single<ApiResponse<User>> {
		return Single.fromCallable<ApiResponse<User>> {
			if (username == USERNAME && password == PASSWORD) {
				val user = User(username)
				ApiResponse.Success(user)
			} else {
				ApiResponse.Error()
			}
		}
			.delay()
	}

	override fun getTweets(): Single<ApiResponse<List<Tweet>>> {
		return Single.fromCallable<ApiResponse<List<Tweet>>> {
			ApiResponse.Success(tweets)
		}
			.delay()
	}

	override fun postTweet(username: String, timeMs: Long, message: String): Single<ApiResponse<Tweet>> {
		val uid = UUID.randomUUID().toString()
		val tweet = Tweet(uid, username, timeMs, message)
		return Single.fromCallable<ApiResponse<Tweet>> {
			tweets.add(tweet)
			ApiResponse.Success(tweet)
		}
			.delay()
	}

	private fun <T> Single<T>.delay(): Single<T> {
		return delay(DELAY_MS, TimeUnit.MILLISECONDS)
	}

	companion object {
		private const val USERNAME = "user"
		private const val PASSWORD = "pass"
		private const val DELAY_MS: Long = 1000
	}
}
