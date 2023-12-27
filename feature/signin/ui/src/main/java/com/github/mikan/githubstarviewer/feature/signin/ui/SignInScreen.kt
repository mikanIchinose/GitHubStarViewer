package com.github.mikan.githubstarviewer.feature.signin.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    activity: Activity,
    viewModel: SignInViewModel = viewModel(),
) {
    val token = viewModel.githubAuthToken
    LaunchedEffect(token) {
        if (token == null) {
            return@LaunchedEffect
        }

        Log.d("MainActivity", "token: $token")
    }

    val coroutineScope = rememberCoroutineScope()
    when (viewModel.uiState) {
        SignInState.SignedOut -> {
            Box {
                Button(onClick = { viewModel.signIn(activity) }) {
                    Text(text = "Sign In With GitHub")
                }
            }
        }

        SignInState.SignedIn -> {
            Column {
                Text(text = "Signed In")
                Button(onClick = {
                    coroutineScope.launch {
                        viewModel.signOut()
                    }
                }) {
                    Text(text = "Sign Out")
                }
            }
        }

        SignInState.Loading -> {
            Box {
                Button(onClick = { viewModel.signIn(activity) }, enabled = false) {
                    Text(text = "Sign Inning...")
                }
            }
        }

        SignInState.Error -> {
            Text(text = "Error")
        }
    }
}

@Composable
fun LoginScreen(
    onClickLoginButton: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box {
//            Icon(
//                imageVector = ImageVector.vectorResource(id = R.drawable.github_icon_light),
//                contentDescription = "github icon",
//            )
        }
        Box(modifier = Modifier.height(24.dp))
        Button(onClick = onClickLoginButton) {
            Text(text = "Log in")
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    Surface {
        LoginScreen(onClickLoginButton = { })
    }
}
