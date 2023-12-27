package com.github.mikan.githubstarviewer.feature.repositories.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import com.github.mikan.githubstarviewer.feature.repositories.domain.usecase.GetStarredRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getStarredRepositoriesUseCase: GetStarredRepositoriesUseCase
) : ViewModel() {
    var repositories: List<RepositoryDomainModel>? by mutableStateOf(null)
        private set

    fun load() {
        viewModelScope.launch {
            getStarredRepositoriesUseCase()
                .collect {
                    repositories = it
                }
        }
    }
}