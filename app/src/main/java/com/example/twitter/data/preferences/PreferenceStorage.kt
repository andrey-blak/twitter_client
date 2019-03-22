package com.example.twitter.data.preferences

import android.content.SharedPreferences
import info.metadude.android.typedpreferences.BooleanPreference
import info.metadude.android.typedpreferences.StringPreference

class PreferenceStorage(
	preferences: SharedPreferences
) {
	private val isLoggedIn = BooleanPreference(preferences, Key.IS_LOGGED_IN.name)
	private val userName = StringPreference(preferences, Key.USERNAME.name)

	fun isLoggedIn(): Boolean {
		return isLoggedIn.get()
	}

	fun setLoggedIn(loggedIn: Boolean) {
		isLoggedIn.set(loggedIn)
	}

	fun getUsername(): String {
		return userName.get()
	}

	fun setUsername(username: String) {
		userName.set(username)
	}

	private enum class Key {
		IS_LOGGED_IN,
		USERNAME,
	}
}
