package com.github.mikan.githubstarviewer.feature.signin.data.impl

import android.app.Activity
import com.github.mikan.githubstarviewer.feature.signin.data.api.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthCredential
import com.google.firebase.auth.OAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val oathProvider: OAuthProvider
) : AuthRepository {
    override var accessToken: String? = null

    override fun signInWithGithub(activity: Activity): Flow<String?> = flow {
        val result = auth.startActivityForSignInWithProvider(activity, oathProvider).await()
        accessToken = (result.credential as OAuthCredential).accessToken
        emit(accessToken)
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun isSignedIn(): Boolean {
        return auth.currentUser != null
    }
}
