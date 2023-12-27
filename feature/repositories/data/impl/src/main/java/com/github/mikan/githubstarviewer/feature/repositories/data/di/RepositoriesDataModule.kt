package com.github.mikan.githubstarviewer.feature.repositories.data.di

import com.github.mikan.githubstarviewer.feature.repositories.data.api.GitHubRepository
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
}