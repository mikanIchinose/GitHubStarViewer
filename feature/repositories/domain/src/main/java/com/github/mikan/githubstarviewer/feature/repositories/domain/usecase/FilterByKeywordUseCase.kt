package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import javax.inject.Inject

class FilterByKeywordUseCase @Inject constructor() {
    operator fun invoke(
        repositories: List<RepositoryDomainModel>,
        include: String = "",
        exclude: String = "",
    ): List<RepositoryDomainModel> {
        if (include.isBlank() && exclude.isBlank()) {
            return repositories
        }

        require(include != exclude) {
            "include and exclude must be different."
        }

        return repositories
            .filterByKeyword(include)
            .filterNotByKeyword(exclude)
    }
}
