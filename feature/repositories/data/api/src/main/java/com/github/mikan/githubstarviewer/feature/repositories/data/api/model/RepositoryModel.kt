package com.github.mikan.githubstarviewer.feature.repositories.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryModel(
    @SerialName("full_name")
    val fullName: String,
    val description: String?,
    val url: String,
    @SerialName("stargazers_count")
    val stars: Int,
    val language: String?,
    @SerialName("languages_url")
    val languagesUrl: String,
    val license: LicenseModel?,
)
