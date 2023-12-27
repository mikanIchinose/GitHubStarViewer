package com.github.mikan.githubstarviewer.feature.repositories.data.api

import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun getStarredRepositories(): Flow<List<RepositoryModel>>
    suspend fun getLanguages(url: String): List<String>
}
