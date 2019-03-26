package com.example.twitter.ui.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> : MvpBasePresenter<T>() {
	private val disposables = CompositeDisposable()

	override fun detachView() {
		super.detachView()
		disposables.clear()
	}

	protected fun addDisposable(disposable: Disposable) {
		disposables.add(disposable)
	}
}
