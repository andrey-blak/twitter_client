package com.example.twitter.ui.tweets

import com.example.twitter.data.dto.Response
import com.example.twitter.domain.usecases.LoadTweetsUseCase
import com.example.twitter.domain.usecases.LogoutUseCase
import com.example.twitter.domain.usecases.ObserveTweetsUseCase
import com.example.twitter.ui.base.BasePresenter
import javax.inject.Inject

class TweetsPresenter @Inject constructor(
	private val loadTweetsUseCase: LoadTweetsUseCase,
	private val logoutUseCase: LogoutUseCase,
	private val observeTweetsUseCase: ObserveTweetsUseCase
) : BasePresenter<TweetsView>() {
	fun init() {
		loadTweets()
		observeTweets()
	}

	private fun loadTweets() {
		ifViewAttached { view ->
			view.showProgress()
			val disposable = loadTweetsUseCase.loadTweets()
				.subscribe({ response ->
					view.hideProgress()
					if (response is Response.Error) {
						view.showError()
					}
				},
					{ throwable ->
						view.hideProgress()
						view.showError()
					})
			addDisposable(disposable)
		}
	}

	fun onNewTweetClicked() {
		ifViewAttached { view ->
			view.goToNewTweet()
		}
	}

	fun onLogoutClicked() {
		ifViewAttached { view ->
			val disposable = logoutUseCase.logout()
				.subscribe {
					view.goToLogin()
				}
			addDisposable(disposable)
		}
	}

	private fun observeTweets() {
		val disposable = observeTweetsUseCase.observeTweets()
			.subscribe { tweets ->
				ifViewAttached { view ->
					view.showTweets(tweets)
				}
			}
		addDisposable(disposable)
	}
}
