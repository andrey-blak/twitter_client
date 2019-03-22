package com.example.twitter.domain.usecases

import com.example.twitter.data.repositories.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class LaunchUseCase @Inject constructor(
	private val userRepository: UserRepository
) {
	fun launch(): Single<Boolean> {
		return userRepository.getIsLoggedIn()
	}
}
