package com.github.mikan.githubstarviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.github.mikan.githubstarviewer.feature.repositories.ui.RepositoriesScreen
import com.github.mikan.githubstarviewer.feature.signin.ui.SignInScreen
import com.github.mikan.githubstarviewer.ui.theme.GitHubStarViewerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubStarViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        SignInScreen(activity = this@MainActivity)
                        RepositoriesScreen()
                    }
                }
            }
        }
    }
}

