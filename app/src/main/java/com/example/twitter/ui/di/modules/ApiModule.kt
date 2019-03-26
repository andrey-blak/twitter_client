package com.example.twitter.ui.di.modules

import com.example.twitter.data.api.MockRestApi
import com.example.twitter.data.api.RestApi
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
	@Provides
	fun provideRestApi(): RestApi {
		return MockRestApi()
	}
}
