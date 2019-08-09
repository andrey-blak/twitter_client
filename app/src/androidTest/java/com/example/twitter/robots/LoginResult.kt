package com.example.twitter.robots

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.twitter.R
import com.example.twitter.espressoktx.onToast

class LoginResult {
	fun checkSuccessful() {
		onView(withId(R.id.tweets_recyclerview))
			.check(matches(isDisplayed()))
	}

	fun checkError(activity: Activity) {
		onToast(activity, R.string.login_login_error)
			.check(matches(isDisplayed()))
	}
}
