package com.github.mikan.githubstarviewer.core.testing.repository

import com.github.mikan.githubstarviewer.feature.repositories.data.model.RepositoryModel
import com.github.mikan.githubstarviewer.feature.repositories.domain.repository.GitHubRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first

class TestGitHubRepository : GitHubRepository {
    private val repositoriesSharedFlow: MutableSharedFlow<List<com.github.mikan.githubstarviewer.feature.repositories.data.model.RepositoryModel>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val languagesSharedFlow: MutableSharedFlow<List<String>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getRepositories(): Flow<List<com.github.mikan.githubstarviewer.feature.repositories.data.model.RepositoryModel>> =
        repositoriesSharedFlow

    override suspend fun getLanguages(url: String): List<String> =
        languagesSharedFlow.first()

    fun sendRepositories(repositories: List<com.github.mikan.githubstarviewer.feature.repositories.data.model.RepositoryModel>) {
        repositoriesSharedFlow.tryEmit(repositories)
    }

    fun sendLanguages(languages: List<String>) {
        languagesSharedFlow.tryEmit(languages)
    }
}