package com.example.twitter.data.preferences

import android.content.SharedPreferences
import info.metadude.android.typedpreferences.BooleanPreference

class PreferenceStorage(
	preferences: SharedPreferences
) {
	private val isLoggedIn: BooleanPreference = BooleanPreference(preferences, Key.IS_LOGGED_IN.name)

	fun isLoggedIn(): Boolean {
		return isLoggedIn.get()
	}

	fun setLoggedIn(loggedIn: Boolean) {
		isLoggedIn.set(loggedIn)
	}

	private enum class Key {
		IS_LOGGED_IN,
	}
}
