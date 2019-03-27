package com.example.twitter.ui.newtweet

import com.example.twitter.data.dto.Response
import com.example.twitter.domain.usecases.SendTweetUseCase
import com.example.twitter.ui.base.BasePresenter
import javax.inject.Inject

class NewTweetPresenter @Inject constructor(
	private val sendTweetUseCase: SendTweetUseCase
) : BasePresenter<NewTweetView>() {
	fun onSendClicked() {
		ifViewAttached { view ->
			val message = view.getMessage()
			view.showProgress()
			val disposable = sendTweetUseCase.send(message)
				.subscribe({ apiResponse ->
					view.hideProgress()
					when (apiResponse) {
						is Response.Success -> {
							view.goToTweets()
						}
						is Response.Error -> {
							view.showError()
						}
					}
				}, { throwable ->
					view.hideProgress()
					view.showError()
				})
			addDisposable(disposable)
		}
	}
}
