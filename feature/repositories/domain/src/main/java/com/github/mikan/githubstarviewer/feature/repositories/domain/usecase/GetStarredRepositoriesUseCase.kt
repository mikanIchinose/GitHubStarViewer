package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.repository.GitHubRepositoryRepository
import javax.inject.Inject

class GetStarredRepositoriesUseCase @Inject constructor(
    private val gitHubRepositoryRepository: GitHubRepositoryRepository,
) {
    operator fun invoke() = gitHubRepositoryRepository.getStarredRepositories()
}
