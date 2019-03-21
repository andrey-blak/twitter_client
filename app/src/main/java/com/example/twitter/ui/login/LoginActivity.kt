package com.example.twitter.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.twitter.R
import com.example.twitter.data.api.MockRestApi
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView {
	companion object {
		@JvmStatic
		fun createIntent(context: Context): Intent {
			return Intent(context, LoginActivity::class.java)
		}
	}

	override fun createPresenter(): LoginPresenter {
		return LoginPresenter(MockRestApi())
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		login_login_button.setOnClickListener { logIn() }
	}

	override fun showProgress() {
		login_progress.isVisible = true
	}

	override fun hideProgress() {
		login_progress.isVisible = false
	}

	override fun goToTweets() {
		// todo implement
	}

	override fun showInvalidCredentialsError() {
		Toast.makeText(this, R.string.login_login_error, Toast.LENGTH_SHORT).show()
	}

	private fun logIn() {
		val username = login_username.text.toString()
		val password = login_password.text.toString()
		presenter.login(username, password)
	}
}
