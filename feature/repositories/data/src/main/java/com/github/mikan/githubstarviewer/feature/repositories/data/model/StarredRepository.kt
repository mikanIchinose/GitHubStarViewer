package com.github.mikan.githubstarviewer.feature.repositories.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarredRepository(
    val id: Int,
    val description: String?,
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    val owner: Owner,
    @SerialName("stargazers_count")
    val stars: Int,
    @SerialName("html_url")
    val url: String,
    val homepage: String?,
    val language: String?,
    @SerialName("languages_url")
    val languagesUrl: String,
    val license: License?,
    val topics: List<String>,
)

@Serializable
data class License(
    val name: String,
)

@Serializable
data class Owner(
    val id: Int,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("login")
    val loginName: String,
)
