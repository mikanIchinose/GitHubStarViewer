package com.github.mikan.githubstarviewer.feature.repositories.data.network

import com.github.mikan.githubstarviewer.feature.repositories.data.model.GitHubError
import com.github.mikan.githubstarviewer.feature.repositories.data.model.StarredRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.serialization.JsonConvertException
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

internal class GitHubApi @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getStarredRepositories(
        userName: String,
        page: Int,
        perPage: Int,
    ): List<StarredRepository> {
        val response = client.get("users/$userName/starred") {
            url {
                parameters.apply {
                    append("page", "$page")
                    append("per_page", "$perPage")
                }
            }
        }
        return try {
            response.body<List<StarredRepository>>()
        } catch (e: JsonConvertException) {
            throw response.body<GitHubError>()
        }
    }

    suspend fun getLanguages(repository: StarredRepository): List<String> {
        val response = client.get(repository.languagesUrl)
        val jsonObject = response.body<JsonObject>()
        return jsonObject.keys.toList()
    }
}
