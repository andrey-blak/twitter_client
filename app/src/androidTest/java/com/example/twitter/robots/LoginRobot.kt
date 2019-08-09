package com.example.twitter.robots

import androidx.test.espresso.ViewInteraction
import com.example.twitter.R
import com.example.twitter.espressoktx.click
import com.example.twitter.espressoktx.onView
import com.example.twitter.espressoktx.replaceText

class LoginRobot {
	fun setUsername(username: String): LoginRobot {
		onUsernameView().replaceText(username)
		return this
	}

	fun setPassword(password: String): LoginRobot {
		onPasswordView().replaceText(password)
		return this
	}

	fun login(): TweetsRobot {
		onLoginButton().click()

		return TweetsRobot()
	}

	private fun onUsernameView(): ViewInteraction {
		return onView(R.id.login_username)
	}

	private fun onPasswordView(): ViewInteraction {
		return onView((R.id.login_password))
	}

	private fun onLoginButton(): ViewInteraction {
		return onView((R.id.login_login_button))
	}
}
