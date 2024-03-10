package com.github.mikan.githubstarviewer.feature.repositories.data.di

import androidx.paging.PagingSource
import com.github.mikan.githubstarviewer.feature.repositories.data.api.GitHubRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.StarredRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.impl.GitHubPagingSource
import com.github.mikan.githubstarviewer.feature.repositories.data.impl.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesDataModule {
    @Binds
    fun bindGitHubRepository(impl: GitHubRepositoryImpl): GitHubRepository

    @Binds
    fun bindPagingSource(pagingSource: GitHubPagingSource): PagingSource<Int, StarredRepository>
}
