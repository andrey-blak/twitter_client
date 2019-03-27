package com.example.twitter.ui.tweets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.R
import com.example.twitter.data.database.Tweet
import com.example.twitter.ui.TwitterApp
import com.example.twitter.ui.login.LoginActivity
import com.example.twitter.ui.newtweet.NewTweetActivity
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_tweets.*
import javax.inject.Inject

class TweetsActivity : MvpActivity<TweetsView, TweetsPresenter>(), TweetsView {
	private lateinit var tweetsAdapter: TweetsAdapter
	@Inject
	lateinit var tweetsPresenter: TweetsPresenter

	override fun createPresenter(): TweetsPresenter {
		return tweetsPresenter
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		TwitterApp.component.inject(this)
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_tweets)

		tweets_add_tweet_button.setOnClickListener { presenter.onNewTweetClicked() }
		tweets_logout_button.setOnClickListener { presenter.onLogoutClicked() }
		initTweets()
	}

	override fun onStart() {
		super.onStart()
		loadTweets()
	}

	override fun showProgress() {
		// todo implement
	}

	override fun hideProgress() {
		// todo implement
	}

	override fun showTweets(tweets: List<Tweet>) {
		tweetsAdapter.setItems(tweets)
	}

	override fun showError() {
		// todo implement
	}

	override fun goToNewTweet() {
		val intent = NewTweetActivity.createIntent(this)
		startActivity(intent)
	}

	override fun goToLogin() {
		val intent = LoginActivity.createIntent(this)
		startActivity(intent)
	}

	private fun initTweets() {
		tweetsAdapter = TweetsAdapter(this)
		tweets_recyclerview.adapter = tweetsAdapter

		tweets_recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
	}

	private fun loadTweets() {
		presenter.loadTweets()
	}

	companion object {
		@JvmStatic
		fun createIntent(context: Context): Intent {
			return Intent(context, TweetsActivity::class.java)
		}
	}
}
