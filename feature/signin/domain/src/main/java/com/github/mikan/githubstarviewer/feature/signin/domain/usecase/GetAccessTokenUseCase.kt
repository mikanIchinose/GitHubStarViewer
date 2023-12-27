package com.github.mikan.githubstarviewer.feature.signin.domain.usecase

import com.github.mikan.githubstarviewer.feature.signin.data.api.AuthRepository
import javax.inject.Inject

class GetAccessTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() =
        authRepository.accessToken
}