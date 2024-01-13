package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import javax.inject.Inject

class FilterByKeywordUseCase @Inject constructor() {
    operator fun invoke(
        repositories: List<RepositoryDomainModel>,
        include: String = "",
        exclude: String = "",
    ): List<RepositoryDomainModel> {
        return repositories
            .filterByKeyword(include)
            .filterNotByKeyword(exclude)
    }
}
