package com.example.twitter.ui.newtweet

import com.hannesdorfmann.mosby3.mvp.MvpView

interface NewTweetView : MvpView {
	fun getMessage(): String
	fun goToTweets()
	fun showProgress()
	fun hideProgress()
	fun showError()
}
