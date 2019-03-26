package com.example.twitter.ui.di

import com.example.twitter.ui.di.modules.ApiModule
import com.example.twitter.ui.login.LoginActivity
import dagger.Component

@Component(modules = [ApiModule::class])
interface AppComponent {
	fun inject(loginActivity: LoginActivity)
}
