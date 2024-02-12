package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.data.api.GitHubRepository
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStarredRepositoriesUseCase @Inject constructor(
    private val repository: GitHubRepository,
) {
    suspend operator fun invoke() =
        repository
            .getStarredRepositories()
            .map { repositories ->
                repositories.map {
                    val languages = repository.getLanguages(it.languagesUrl)
                    it.toDomainModel(languages)
                }
            }
}
