package com.example.twitter.data.preferences

import android.content.SharedPreferences
import info.metadude.android.typedpreferences.BooleanPreference
import info.metadude.android.typedpreferences.StringPreference

class PreferenceStorage(
	private val preferences: SharedPreferences
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

	fun clear() {
		val editor = preferences.edit()
		for (value in Key.values()) {
			editor.remove(value.name)
		}
		editor.apply()
	}

	/**
	 * The enum's instances' names are used as shared preferences keys to ensure uniqueness.
	 * The disadvantage is that they cannot be renamed to maintain compatibility.
	 */
	private enum class Key {
		IS_LOGGED_IN,
		USERNAME,
	}
}
