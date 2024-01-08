package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import javax.inject.Inject

class FilterNotByKeywordUseCase @Inject constructor() {
    operator fun invoke(
        keyword: String,
        repositories: List<RepositoryDomainModel>,
    ): List<RepositoryDomainModel> {
        return repositories.filterNot {
            it.nameWithOwner.contains(keyword) || it.description?.contains(keyword) == true
        }
    }
}
