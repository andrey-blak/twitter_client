package com.example.twitter.domain.usecases

import androidx.annotation.CheckResult
import com.example.twitter.data.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
	private val userRepository: UserRepository
) {
	@CheckResult
	fun logout(): Completable {
		return userRepository.clearUser()
			.observeOn(AndroidSchedulers.mainThread())
	}
}
