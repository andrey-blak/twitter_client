package com.example.twitter.ui.login

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import com.example.twitter.R
import com.example.twitter.daggerMockRule
import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.dto.User
import com.example.twitter.espressoktx.onToast
import com.example.twitter.robots.LoginRobot
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
		val username = "asd@asd.asd"
		val validPassword = "valid_password"
		whenever(api.login(username, validPassword))
			.thenReturn(Single.just(ApiResponse.Success(User(""))))
		whenever(api.getTweets())
			.thenReturn(Single.just(ApiResponse.Success(listOf())))

		activityRule.launchActivity(null)

		val result = LoginRobot()
			.setUsername(username)
			.setPassword(validPassword)
			.login()

		result.checkSuccessful()
	}

	@Test
	fun testInvalidLogin() {
		val username = "asd@asd.asd"
		val invalidPassword = "invalid_password"
		whenever(api.login(username, invalidPassword))
			.thenReturn(Single.just(ApiResponse.Error()))

		activityRule.launchActivity(null)
		LoginRobot()
			.setUsername(username)
			.setPassword(invalidPassword)
			.login()

		onToast(activityRule.activity, R.string.login_login_error)
			.check(matches(isDisplayed()))
	}
}
