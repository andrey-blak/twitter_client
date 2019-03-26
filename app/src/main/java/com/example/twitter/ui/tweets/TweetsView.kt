package com.example.twitter.ui.tweets

import com.example.twitter.data.dto.Tweet
import com.hannesdorfmann.mosby3.mvp.MvpView

interface TweetsView : MvpView {
	fun showProgress()
	fun hideProgress()
	fun showTweets(tweets: List<Tweet>)
	fun showError()
}
