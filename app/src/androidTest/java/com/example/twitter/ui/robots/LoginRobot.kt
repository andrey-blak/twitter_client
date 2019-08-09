package com.example.twitter.ui.robots

import android.app.Activity
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.twitter.R
import com.example.twitter.ui.espressoktx.click
import com.example.twitter.ui.espressoktx.onToast
import com.example.twitter.ui.espressoktx.onView
import com.example.twitter.ui.espressoktx.replaceText

fun loginRobot(action: LoginRobot.() -> Unit) = LoginRobot().apply(action)

infix fun LoginRobot.verify(action: LoginResult.() -> Unit) : LoginResult = LoginResult().apply(action)

class LoginRobot {
	fun setUsername(username: String): LoginRobot {
		onUsernameView().replaceText(username)
		return this
	}

	fun setPassword(password: String): LoginRobot {
		onPasswordView().replaceText(password)
		return this
	}

	fun login(): LoginRobot {
		onLoginButton().click()
		return this
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

class LoginResult {
	fun checkError(activity: Activity) {
		onToast(activity, R.string.login_login_error)
			.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
	}
}
