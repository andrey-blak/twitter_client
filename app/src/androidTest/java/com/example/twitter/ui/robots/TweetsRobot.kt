package com.example.twitter.ui.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.twitter.R

fun tweetsRobot(action: TweetsRobot.() -> Unit) = TweetsRobot().run(action)

class TweetsRobot {
	fun checkSuccessful() {
		onView(withId(R.id.tweets_recyclerview))
			.check(matches(isDisplayed()))
	}
}
