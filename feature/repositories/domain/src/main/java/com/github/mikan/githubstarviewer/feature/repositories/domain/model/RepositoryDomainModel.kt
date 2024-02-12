package com.github.mikan.githubstarviewer.feature.repositories.domain.model

import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.RepositoryModel
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.StarredRepository

data class RepositoryDomainModel(
    val nameWithOwner: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val language: String?,
    val languages: List<String>,
    val license: String?,
)

internal fun RepositoryModel.toDomainModel(languages: List<String>) =
    RepositoryDomainModel(
        nameWithOwner = fullName,
        description = description,
        url = url,
        stars = stars,
        language = language,
        languages = languages,
        license = license?.name,
    )

internal fun StarredRepository.toDomainModel(languages: List<String>) =
    RepositoryDomainModel(
        nameWithOwner = fullName,
        description = description,
        url = url,
        stars = stars,
        language = language,
        languages = languages,
        license = license?.name,
    )
