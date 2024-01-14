package com.github.mikan.githubstarviewer.feature.repositories.data.network

import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.StarredRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

internal class GitHubApi @Inject constructor(
    private val client: HttpClient,
) {
    fun getStarredRepositories(
        userName: String,
        page: Int,
        perPage: Int = 100,
    ): Flow<List<StarredRepository>> = flow {
        val response = client.get("users/$userName/starred") {
            url {
                parameters.apply {
                    append("page", "$page")
                    append("per_page", "$perPage")
                }
            }
        }
        emit(response.body<List<StarredRepository>>())
    }

    fun getLanguages(repository: StarredRepository): Flow<List<String>> = flow {
        val response = client.get(repository.languagesUrl)
        val jsonObject = response.body<JsonObject>()
        emit(jsonObject.keys.toList())
    }
}
