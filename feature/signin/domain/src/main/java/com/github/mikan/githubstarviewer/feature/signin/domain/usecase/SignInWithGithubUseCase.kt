package com.github.mikan.githubstarviewer.feature.signin.domain.usecase

import android.app.Activity
import com.github.mikan.githubstarviewer.feature.signin.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SignInWithGithubUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(activity: Activity): Flow<String> = flowOf(Unit)
        .flatMapConcat {
            authRepository.signInWithGithub(activity)
        }
        .map {
            if (it == null) {
                throw IllegalStateException("Access token is null.")
            }
            it
        }
}
