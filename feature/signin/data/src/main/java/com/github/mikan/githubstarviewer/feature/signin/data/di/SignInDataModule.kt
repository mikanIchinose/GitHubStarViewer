package com.github.mikan.githubstarviewer.feature.signin.data.di

import com.github.mikan.githubstarviewer.feature.signin.data.impl.AuthRepositoryImpl
import com.github.mikan.githubstarviewer.feature.signin.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SignInDataModule {
    @Binds
    fun bindSignInDataRepository(impl: AuthRepositoryImpl): AuthRepository
}
