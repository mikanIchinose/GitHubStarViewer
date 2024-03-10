package com.github.mikan.githubstarviewer.feature.repositories.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.github.mikan.githubstarviewer.feature.repositories.domain.usecase.GetStarredRepositoryPagingDataUseCase
import com.github.mikan.githubstarviewer.feature.repositories.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    getStarredRepositoryPagingDataUseCase: GetStarredRepositoryPagingDataUseCase,
) : ViewModel() {
    internal val items =
        getStarredRepositoryPagingDataUseCase()
            .map {
                it.map { domainModel ->
                    domainModel.toUiModel()
                }
            }
            .cachedIn(viewModelScope)
}
