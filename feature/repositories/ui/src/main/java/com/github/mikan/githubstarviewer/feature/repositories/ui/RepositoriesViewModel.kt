package com.github.mikan.githubstarviewer.feature.repositories.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.github.mikan.githubstarviewer.feature.repositories.domain.usecase.GetStarredRepositoriesUseCase
import com.github.mikan.githubstarviewer.feature.repositories.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    getStarredRepositoriesUseCase: GetStarredRepositoriesUseCase,
) : ViewModel() {
    internal val items =
        getStarredRepositoriesUseCase()
            .map {
                it.map { domainModel ->
                    domainModel.toUiModel()
                }
            }
            .cachedIn(viewModelScope)
}
