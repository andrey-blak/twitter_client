package com.example.twitter.ui

import android.app.Application
import com.example.twitter.ui.di.AppComponent
import com.example.twitter.ui.di.DaggerAppComponent
import com.example.twitter.ui.di.modules.PreferencesModule

class TwitterApp : Application() {
	override fun onCreate() {
		super.onCreate()

		initDependencies()
	}

	private fun initDependencies() {
		component = DaggerAppComponent.builder()
			.preferencesModule(PreferencesModule(applicationContext))
			.build()
	}

	companion object {
		lateinit var component: AppComponent
	}
}
