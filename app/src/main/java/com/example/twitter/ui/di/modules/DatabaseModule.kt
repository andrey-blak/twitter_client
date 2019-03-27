package com.example.twitter.ui.di.modules

import android.content.Context
import com.example.twitter.BuildConfig
import com.example.twitter.data.database.MyObjectBox
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import javax.inject.Singleton

@Module
class DatabaseModule(
	private val context: Context
) {
	@Singleton
	@Provides
	fun provideBoxStore(): BoxStore {
		val boxStore = MyObjectBox.builder()
			.androidContext(context)
			.build()
		if (BuildConfig.DEBUG) {
			AndroidObjectBrowser(boxStore).start(context)
		}
		return boxStore
	}
}
