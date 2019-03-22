package com.example.twitter.ui.di

import com.example.twitter.ui.di.modules.ApiModule
import com.example.twitter.ui.di.modules.PreferencesModule
import com.example.twitter.ui.login.LoginActivity
import com.example.twitter.ui.newtweet.NewTweetActivity
import com.example.twitter.ui.splash.SplashActivity
import com.example.twitter.ui.tweets.TweetsActivity
import dagger.Component

@Component(
	modules = [
		ApiModule::class,
		PreferencesModule::class
	]
)
interface AppComponent {
	fun inject(loginActivity: LoginActivity)
	fun inject(loginActivity: TweetsActivity)
	fun inject(splashActivity: SplashActivity)
	fun inject(newTweetActivity: NewTweetActivity)
}
