package com.github.mikan.githubstarviewer.feature.repositories.domain.repository

import androidx.paging.PagingData
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import kotlinx.coroutines.flow.Flow

typealias StarredRepositoryPagingData = PagingData<RepositoryDomainModel>

interface GitHubRepositoryRepository {
    fun getStarredRepositories(): Flow<StarredRepositoryPagingData>
}
