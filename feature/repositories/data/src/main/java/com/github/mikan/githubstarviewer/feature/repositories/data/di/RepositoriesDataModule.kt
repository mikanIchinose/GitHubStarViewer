package com.github.mikan.githubstarviewer.feature.repositories.data.di

import androidx.paging.PagingSource
import com.github.mikan.githubstarviewer.feature.repositories.data.impl.GitHubPagingSource
import com.github.mikan.githubstarviewer.feature.repositories.data.impl.GitHubRepositoryRepositoryImpl
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import com.github.mikan.githubstarviewer.feature.repositories.domain.repository.GitHubRepositoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoriesDataModule {
    @Binds
    fun bindGitHubRepository(impl: GitHubRepositoryRepositoryImpl): GitHubRepositoryRepository

    @Binds
    fun bindPagingSource(pagingSource: GitHubPagingSource): PagingSource<Int, RepositoryDomainModel>
}
