package com.example.twitter.ui.login

import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.domain.usecases.LoginUseCase
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(
	// todo inject
	val api: RestApi
) : MvpBasePresenter<LoginView>() {
	private val disposables = CompositeDisposable()

	override fun detachView() {
		super.detachView()
		disposables.clear()
	}

	public fun login(username: String, password: String) {
		val loginUseCase = LoginUseCase(api)
		ifViewAttached {view ->
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
			disposables.add(disposable)
		}
	}
}
