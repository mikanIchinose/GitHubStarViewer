package com.github.mikan.githubstarviewer.feature.repositories.data.di

import com.github.mikan.githubstarviewer.feature.repositories.data.model.UserName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserNameModule {
    @Provides
    fun providesUserName(): UserName {
        return UserName("mikanIchinose")
    }
}
