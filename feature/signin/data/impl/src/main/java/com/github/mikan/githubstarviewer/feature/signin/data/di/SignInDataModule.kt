package com.github.mikan.githubstarviewer.feature.signin.data.di

import com.github.mikan.githubstarviewer.feature.signin.data.api.AuthRepository
import com.github.mikan.githubstarviewer.feature.signin.data.impl.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignInDataModule {
    @Binds
    fun bindSignInDataRepository(
        repository: AuthRepositoryImpl
    ): AuthRepository
}