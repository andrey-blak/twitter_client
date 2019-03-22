package com.example.twitter.domain.usecases

import androidx.annotation.CheckResult
import com.example.twitter.data.api.ApiResponse
import com.example.twitter.data.api.RestApi
import com.example.twitter.data.dto.User
import com.example.twitter.data.repositories.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(
	private val api: RestApi,
	private val userRepository: UserRepository
) {
	@CheckResult
	public fun login(username: String, password: String): Single<ApiResponse<User>> {
		return api.login(username, password)
			.subscribeOn(Schedulers.io()) // todo inject
			.flatMap { response ->
				when (response) {
					is ApiResponse.Success -> {
						Completable.mergeArray(
							userRepository.setIsLoggedIn(true),
							userRepository.setUsername(response.data.username)
						).andThen(Single.just(response))
					}
					is ApiResponse.Error -> {
						Single.just(response)
					}
				}
			}
			.observeOn(AndroidSchedulers.mainThread()) // todo inject
	}
}
