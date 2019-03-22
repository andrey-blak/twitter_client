package com.example.twitter.domain.usecases

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.dto.User
import com.example.twitter.ui.login.LoginPresenter
import com.example.twitter.ui.login.LoginView
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginPresenterTest {
	@Mock
	private lateinit var loginUseCase: LoginUseCase
	@Mock
	private lateinit var loginView: LoginView
	private lateinit var presenter: LoginPresenter

	@Before
	fun setup() {
		MockitoAnnotations.initMocks(this)

		presenter = LoginPresenter(loginUseCase)
		presenter.attachView(loginView)
	}

	@Test
	fun shouldShowErrorWhenLoginFailed() {
		whenever(loginUseCase.login(any(), any()))
			.thenReturn(Single.just(ApiResponse.Error()))

		val username = "any"
		val password = "any"
		presenter.login(username, password)

		verify(loginView).showInvalidCredentialsError()
	}

	@Test
	fun shouldGoToTweetsWhenLoggedIn() {
		val user = User("")
		whenever(loginUseCase.login(any(), any()))
			.thenReturn(Single.just(ApiResponse.Success(user)))

		val username = "any"
		val password = "any"
		presenter.login(username, password)

		verify(loginView).goToTweets()
	}
}
