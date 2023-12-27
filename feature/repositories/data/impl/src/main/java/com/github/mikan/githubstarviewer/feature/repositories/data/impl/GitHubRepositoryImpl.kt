package com.github.mikan.githubstarviewer.feature.repositories.data.impl

import com.github.mikan.githubstarviewer.feature.repositories.data.api.GitHubRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.RepositoryModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

private const val USER_NAME = "mikanIchinose"

class GitHubRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : GitHubRepository {
    override suspend fun getStarredRepositories(): Flow<List<RepositoryModel>> = flow {
        val response = client.get("users/$USER_NAME/starred")
        emit(response.body<List<RepositoryModel>>())
    }

    override suspend fun getLanguages(url: String): List<String> {
        val response = client.get(url)
        val jsonObject = response.body<JsonObject>()
        return jsonObject.keys.toList()
    }
}
