package com.example.twitter.data.repositories

import androidx.annotation.CheckResult
import com.example.twitter.data.preferences.PreferenceStorage
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(
	private val preferences: PreferenceStorage
) {
	@CheckResult
	fun setIsLoggedIn(isLoggedIn: Boolean): Completable {
		return Completable.fromAction {
			preferences.setLoggedIn(isLoggedIn)
		}
	}

	@CheckResult
	fun getIsLoggedIn() : Single<Boolean> {
		return Single.fromCallable {
			preferences.isLoggedIn()
		}
	}

	@CheckResult
	fun setUsername(username: String): Completable {
		return Completable.fromAction {
			preferences.setUsername(username)
		}
	}

	@CheckResult
	fun getUsername(): Single<String> {
		return Single.fromCallable {
			preferences.getUsername()
		}
	}

	@CheckResult
	fun clearUser(): Completable {
		return Completable.fromAction {
			preferences.clear()
		}
	}
}
