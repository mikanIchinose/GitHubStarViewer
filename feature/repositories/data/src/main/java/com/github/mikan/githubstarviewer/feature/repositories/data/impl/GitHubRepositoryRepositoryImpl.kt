package com.github.mikan.githubstarviewer.feature.repositories.data.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.github.mikan.githubstarviewer.feature.repositories.domain.repository.GitHubRepositoryRepository
import com.github.mikan.githubstarviewer.feature.repositories.domain.repository.StarredRepositoryPagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GitHubRepositoryRepositoryImpl @Inject constructor(
    private val pagingSource: GitHubPagingSource,
) : GitHubRepositoryRepository {
    override fun getStarredRepositories(): Flow<StarredRepositoryPagingData> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { pagingSource },
        ).flow
    }
}
