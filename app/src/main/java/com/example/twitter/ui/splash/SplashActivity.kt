package com.example.twitter.ui.splash

import android.os.Bundle
import com.example.twitter.ui.TwitterApp
import com.example.twitter.ui.login.LoginActivity
import com.example.twitter.ui.tweets.TweetsActivity
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import javax.inject.Inject

class SplashActivity : MvpActivity<SplashView, SplashPresenter>(), SplashView {
	@Inject
	lateinit var splashPresenter: SplashPresenter

	override fun createPresenter(): SplashPresenter {
		return splashPresenter
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		TwitterApp.component.inject(this)
		super.onCreate(savedInstanceState)

		presenter.onLaunch()
	}

	override fun goToLoginScreen() {
		startActivity(LoginActivity.createIntent(this))
		finish()
	}

	override fun goToTweets() {
		startActivity(TweetsActivity.createIntent(this))
		finish()
	}
}
