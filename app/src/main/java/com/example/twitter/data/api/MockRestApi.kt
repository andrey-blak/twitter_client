package com.example.twitter.data.api

import com.example.twitter.data.dto.Tweet
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class MockRestApi : RestApi {
	override fun login(username: String, password: String): Single<ApiResponse<out Any?>> {
		return Single.fromCallable {
			if (username == USERNAME && password == PASSWORD) {
				ApiResponse.Success<Any?>(null)
			} else {
				ApiResponse.Error<Void>()
			}
		}
			.delay(DELAY_MS, TimeUnit.MILLISECONDS)
	}

	override fun getTweets(): Single<ApiResponse<List<Tweet>>> {
		return Single.fromCallable<ApiResponse<List<Tweet>>> {
			val tweets = listOf(
				Tweet("Grace Merrill", 1553186389000, "mollit in do ea proident sunt excepteur officia laboris ipsum cupidatat ullamco labore duis exercitation proident"),
				Tweet("Raquel Frost", 1402741018710, "sunt ea minim tempor excepteur ut Lorem nisi magna reprehenderit occaecat proident veniam elit nisi mollit"),
				Tweet("Carol Clay", 1473686390161, "proident sunt non occaecat sit cupidatat quis id cupidatat minim aliqua aute amet anim fugiat enim"),
				Tweet("Sexton Whitfield", 1435376998106, "sit aliqua do in dolore culpa eu eu non labore esse deserunt consequat mollit ea magna"),
				Tweet("Hendricks Stevens", 1391267804940, "do labore laborum adipisicing irure id deserunt commodo non aliqua enim non sunt ex laborum ut"),
				Tweet("Wallace Rivera", 1515572847338, "velit cillum do culpa consectetur magna consequat dolor amet nostrud minim sint ullamco sunt est exercitation"),
				Tweet("Gabrielle Mosley", 1513495118524, "ut commodo dolore deserunt pariatur minim veniam aliquip do minim deserunt magna culpa et occaecat aliqua"),
				Tweet("Joann Carpenter", 1422466644070, "reprehenderit ut ipsum non cillum laborum dolore pariatur commodo aliqua culpa excepteur dolor dolore laborum eiusmod"),
				Tweet("Ola Trevino", 1478898255586, "ex deserunt ad consequat cupidatat labore amet amet veniam culpa esse ullamco exercitation culpa sunt sunt"),
				Tweet("Ford Rice", 1456090019926, "tempor adipisicing ut ut enim laboris incididunt occaecat velit est adipisicing duis cupidatat nostrud quis adipisicing")
			)

			ApiResponse.Success(tweets)
		}
			.delay(DELAY_MS, TimeUnit.MILLISECONDS)
	}

	companion object {
		private const val USERNAME = "user"
		private const val PASSWORD = "pass"
		private const val DELAY_MS: Long = 1000
	}
}
