package com.example.twitter.ui.newtweet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.twitter.R
import com.example.twitter.ui.TwitterApp
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_new_tweet.*
import javax.inject.Inject

class NewTweetActivity : MvpActivity<NewTweetView, NewTweetPresenter>(), NewTweetView {
	@Inject
	lateinit var newTweetPresenter: NewTweetPresenter

	override fun createPresenter(): NewTweetPresenter {
		return newTweetPresenter
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		TwitterApp.component.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_new_tweet)

		new_tweet_send_button.setOnClickListener { presenter.onSendClicked() }
	}

	override fun getMessage(): String {
		return new_tweet_input.text.toString()
	}

	override fun goToTweets() {
		finish()
	}

	override fun showProgress() {
		// todo implement
	}

	override fun hideProgress() {
		// todo implement
	}

	override fun showError() {
		// todo implement
	}

	companion object {
		@JvmStatic
		fun createIntent(context: Context): Intent {
			return Intent(context, NewTweetActivity::class.java)
		}
	}
}
