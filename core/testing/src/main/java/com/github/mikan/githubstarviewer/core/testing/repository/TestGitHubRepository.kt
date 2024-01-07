package com.github.mikan.githubstarviewer.core.testing.repository

import com.github.mikan.githubstarviewer.feature.repositories.data.api.GitHubRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.RepositoryModel
import kotlinx.coroutines.flow.Flow

class TestGitHubRepository : GitHubRepository {
    override suspend fun getStarredRepositories(): Flow<List<RepositoryModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLanguages(url: String): List<String> {
        TODO("Not yet implemented")
    }
}