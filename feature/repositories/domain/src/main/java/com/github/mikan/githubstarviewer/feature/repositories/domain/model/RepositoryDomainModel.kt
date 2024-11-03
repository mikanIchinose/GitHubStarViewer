package com.github.mikan.githubstarviewer.feature.repositories.domain.model

data class RepositoryDomainModel(
    val nameWithOwner: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val language: String?,
    val languages: List<String>,
    val license: String?,
)
