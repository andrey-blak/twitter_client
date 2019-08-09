package com.example.twitter.ui.login

import android.app.Activity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.twitter.R
import com.example.twitter.daggerMockRule
import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.dto.User
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {
	@JvmField
	@Rule
	val daggerMockRule = daggerMockRule()

	@JvmField
	@Rule
	val activityRule = ActivityTestRule(LoginActivity::class.java, false, false)

	private val api: RestApi = mock()

	@Test
	fun testSuccessfulLogin() {
		whenever(api.login(any(), any()))
			.thenReturn(Single.just(ApiResponse.Success(User(""))))
		whenever(api.getTweets())
			.thenReturn(Single.just(ApiResponse.Success(listOf())))

		activityRule.launchActivity(null)

		Espresso.closeSoftKeyboard()

		onView(withId(R.id.login_login_button))
			.perform(click())

		onView(withId(R.id.tweets_recyclerview))
			.check(matches(isDisplayed()))
	}

	@Test
	fun testInvalidLogin() {
		whenever(api.login(any(), any()))
			.thenReturn(Single.just(ApiResponse.Error()))

		activityRule.launchActivity(null)

		onView(withId(R.id.login_username))
			.perform(replaceText("asd@asd.com"), closeSoftKeyboard())

		onView(withId(R.id.login_password))
			.perform(replaceText("invalid_password"), closeSoftKeyboard())

		onView(withId(R.id.login_login_button))
			.perform(click())

		onToast(activityRule.activity, R.string.login_login_error)
			.check(matches(isDisplayed()))
	}

	private fun onToast(activity: Activity, messageResId: Int): ViewInteraction {
		return onView(withText(messageResId))
			.inRoot(withDecorView(not(`is`(activity.window.decorView))))
	}
}
