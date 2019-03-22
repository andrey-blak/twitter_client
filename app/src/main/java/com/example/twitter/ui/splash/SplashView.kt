package com.example.twitter.ui.splash

import com.hannesdorfmann.mosby3.mvp.MvpView

interface SplashView: MvpView {
	fun goToLoginScreen()
	fun goToTweets()
}
