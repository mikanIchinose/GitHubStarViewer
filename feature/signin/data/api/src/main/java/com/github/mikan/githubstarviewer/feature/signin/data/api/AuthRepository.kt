package com.github.mikan.githubstarviewer.feature.signin.data.api

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    var accessToken: String?
    fun signInWithGithub(activity: Activity): Flow<String?>
    fun signOut()
    fun isSignedIn(): Boolean
}
