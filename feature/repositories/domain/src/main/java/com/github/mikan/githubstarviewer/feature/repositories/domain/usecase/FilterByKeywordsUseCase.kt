package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import javax.inject.Inject

class FilterByKeywordsUseCase @Inject constructor() {
    operator fun invoke(
        include: String = "",
        exclude: String = "",
        repositories: List<RepositoryDomainModel>,
    ): List<RepositoryDomainModel> {
        return repositories
            .filterByKeyword(include)
            .filterNotByKeyword(exclude)
    }
}
