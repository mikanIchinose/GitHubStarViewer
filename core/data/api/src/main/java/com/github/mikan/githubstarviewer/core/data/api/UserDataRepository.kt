package com.github.mikan.githubstarviewer.core.data.api

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val accessToken: Flow<String>
}
