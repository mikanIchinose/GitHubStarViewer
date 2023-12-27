package com.github.mikan.githubstarviewer.feature.signin.ui

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikan.githubstarviewer.feature.signin.domain.usecase.IsSignedInUseCase
import com.github.mikan.githubstarviewer.feature.signin.domain.usecase.SignInWithGithubUseCase
import com.github.mikan.githubstarviewer.feature.signin.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGithubUseCase: SignInWithGithubUseCase,
    private val signOutUseCase: SignOutUseCase,
    isSignedInUseCase: IsSignedInUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(if (isSignedInUseCase()) SignInState.SignedIn else SignInState.SignedOut)
        private set
    var githubAuthToken: String? by mutableStateOf(null)
        private set

    suspend fun signOut() {
        uiState = SignInState.Loading
        signOutUseCase()
        delay(1000)
        uiState = SignInState.SignedOut
    }

    fun signIn(activity: Activity) {
        uiState = SignInState.Loading
        viewModelScope.launch {
            signInWithGithubUseCase(activity).catch {
                Log.e("SignInViewModel", "fail signInWithGithubUseCase", it)
            }.collect { token ->
                if (token.isNotEmpty()) {
                    uiState = SignInState.SignedIn
                    githubAuthToken = token
                }
            }
        }
    }
}

enum class SignInState {
    SignedIn,
    SignedOut,
    Loading,
    Error,
}