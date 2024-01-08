package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import javax.inject.Inject

class FilterByKeywordUseCase @Inject constructor() {
    operator fun invoke(
        keyword: String,
        repositories: List<RepositoryDomainModel>,
    ): List<RepositoryDomainModel> {
        return repositories.filterByKeyword(keyword)
    }
}
