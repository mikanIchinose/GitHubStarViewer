package com.github.mikan.githubstarviewer.feature.repositories.data.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.mikan.githubstarviewer.feature.repositories.data.model.GitHubError
import com.github.mikan.githubstarviewer.feature.repositories.data.model.StarredRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.model.UserName
import com.github.mikan.githubstarviewer.feature.repositories.data.network.GitHubApi
import com.github.mikan.githubstarviewer.feature.repositories.domain.model.RepositoryDomainModel
import java.io.IOException
import javax.inject.Inject

class GitHubPagingSource @Inject internal constructor(
    private val gitHubApi: GitHubApi,
    private val userName: UserName,
) : PagingSource<Int, RepositoryDomainModel>() {
    companion object {
        private const val FIRST_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryDomainModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryDomainModel> {
        val page = params.key ?: FIRST_INDEX
        return try {
            val starredRepositories =
                gitHubApi.getStarredRepositories(
                    userName = userName.value,
                    page = page,
                    perPage = params.loadSize
                ).map {
                    val languages = gitHubApi.getLanguages(it)
                    it.toDomainModel(languages)
                }
            LoadResult.Page(
                data = starredRepositories,
                prevKey = if (page == FIRST_INDEX) null else page - 1,
                nextKey = if (starredRepositories.isEmpty()) null else page + 1
            )
        } catch (e: GitHubError) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}

internal fun StarredRepository.toDomainModel(
    languages: List<String>,
) =
    RepositoryDomainModel(
        nameWithOwner = fullName,
        description = description,
        url = url,
        stars = stars,
        language = language,
        languages = languages,
        license = license?.name,
    )
