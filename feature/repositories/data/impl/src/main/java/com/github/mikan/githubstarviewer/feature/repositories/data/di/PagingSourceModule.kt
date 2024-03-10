package com.github.mikan.githubstarviewer.feature.repositories.data.di

import androidx.paging.PagingSource
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.StarredRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.impl.GitHubPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PagingSourceModule {
    @Provides
    fun providePagingSource(pagingSource: GitHubPagingSource): PagingSource<Int, StarredRepository> {
        return pagingSource
    }
}
