package com.example.twitter.ui.login

import com.hannesdorfmann.mosby3.mvp.MvpView

interface LoginView : MvpView {
	fun showProgress()
	fun hideProgress()
	fun goToTweets()
	fun showInvalidCredentialsError()
}
