package com.example.twitter.ui

import android.app.Application
import com.example.twitter.ui.di.AppComponent
import com.example.twitter.ui.di.DaggerAppComponent

class TwitterApp : Application() {
	override fun onCreate() {
		super.onCreate()

		initDependencies()
	}

	private fun initDependencies() {
		component = DaggerAppComponent.builder()
			.build()
	}

	companion object {
		lateinit var component: AppComponent
	}
}
