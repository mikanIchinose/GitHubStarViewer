package com.github.mikan.githubstarviewer.feature.repositories.domain.usecase

import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel

internal fun List<RepositoryDomainModel>.filterByKeyword(keyword: String): List<RepositoryDomainModel> {
    return filter {
        it.nameWithOwner.contains(keyword) || it.description?.contains(keyword) == true
    }
}

internal fun List<RepositoryDomainModel>.filterNotByKeyword(keyword: String): List<RepositoryDomainModel> {
    return filterNot {
        it.nameWithOwner.contains(keyword) || it.description?.contains(keyword) == true
    }
}
