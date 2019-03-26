package com.example.twitter.ui.login

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.domain.usecases.LoginUseCase
import com.example.twitter.ui.base.BasePresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor(
	private val loginUseCase: LoginUseCase
) : BasePresenter<LoginView>() {
	public fun login(username: String, password: String) {
		ifViewAttached { view ->
			view.showProgress()
			val disposable = loginUseCase.login(username, password)
				.subscribe(
					{ response ->
						view.hideProgress()
						when (response) {
							is ApiResponse.Success -> view.goToTweets()
							is ApiResponse.Error -> view.showInvalidCredentialsError()
						}
					},
					{ throwable ->
						view.hideProgress()
						view.showInvalidCredentialsError()
					})
			addDisposable(disposable)
		}
	}
}
