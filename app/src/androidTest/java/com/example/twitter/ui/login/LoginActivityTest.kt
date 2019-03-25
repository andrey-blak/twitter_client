package com.example.twitter.ui.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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

		Espresso.onView(ViewMatchers.withId(R.id.login_login_button))
			.perform(ViewActions.click())

		Espresso.onView(ViewMatchers.withId(R.id.tweets_recyclerview))
			.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
	}
}
