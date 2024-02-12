package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingSource
import androidx.paging.map
import com.github.mikan.githubstarviewer.feature.repositories.data.api.GitHubRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.StarredRepository
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.toDomainModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetStarredRepositoryPagingDataUseCase @Inject constructor(
    private val repository: GitHubRepository,
    private val pagingSource: PagingSource<Int, StarredRepository>,
) {
    operator fun invoke() = Pager(
        config = androidx.paging.PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = { pagingSource },
    )
        .flow
        .map {
            it.map { starredRepository ->
                val languages = repository.getLanguages(starredRepository.languagesUrl)
                starredRepository.toDomainModel(languages)
            }
        }
}
