package com.github.mikan.githubstarviewer.feature.repositories.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GitHubError(
    override val message: String,
    val status: String,
) : Throwable()
