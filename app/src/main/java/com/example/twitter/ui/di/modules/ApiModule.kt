package com.example.twitter.ui.di.modules

import com.example.twitter.data.api.MockRestApi
import com.example.twitter.data.api.RestApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
	@Singleton
	@Provides
	fun provideRestApi(): RestApi {
		return MockRestApi()
	}
}
