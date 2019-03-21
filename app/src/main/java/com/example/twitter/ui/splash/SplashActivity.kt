package com.example.twitter.ui.splash

import android.app.Activity
import android.os.Bundle
import com.example.twitter.ui.login.LoginActivity

class SplashActivity : Activity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		startActivity(LoginActivity.createIntent(this))
		finish()
	}
}
