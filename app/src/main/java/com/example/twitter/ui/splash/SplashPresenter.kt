package com.example.twitter.ui.splash

import com.example.twitter.domain.usecases.LaunchUseCase
import com.example.twitter.ui.base.BasePresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor(
	private val launchUseCase: LaunchUseCase
) : BasePresenter<SplashView>() {
	fun onLaunch() {
		ifViewAttached { view ->
			val disposable = launchUseCase.launch()
				.subscribe { isLoggedIn ->
					if (isLoggedIn) {
						view.goToTweets()
					} else {
						view.goToLoginScreen()
					}
				}
			addDisposable(disposable)
		}
	}
}
