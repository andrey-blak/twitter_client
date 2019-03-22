package com.example.twitter.ui.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.twitter.data.preferences.PreferenceStorage
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule (
	private val context: Context
) {
	@Provides
	fun providePreferenceStorage(): PreferenceStorage {
		val preferences = context.getSharedPreferences("preferences", MODE_PRIVATE)
		return PreferenceStorage(preferences)
	}
}

