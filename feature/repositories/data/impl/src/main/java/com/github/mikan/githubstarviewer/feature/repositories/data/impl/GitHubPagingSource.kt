package com.github.mikan.githubstarviewer.feature.repositories.data.impl

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.mikan.githubstarviewer.feature.repositories.data.api.model.StarredRepository
import com.github.mikan.githubstarviewer.feature.repositories.data.network.GitHubApi
import java.io.IOException
import javax.inject.Inject

class GitHubPagingSource @Inject internal constructor(
    private val gitHubApi: GitHubApi,
) : PagingSource<Int, StarredRepository>() {
    companion object {
        private const val FIRST_INDEX = 1
        private const val USER = "mikanIchinose"
    }

    override fun getRefreshKey(state: PagingState<Int, StarredRepository>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarredRepository> {
        val page = params.key ?: FIRST_INDEX
        return try {
            val starredRepositories = gitHubApi.getStarredRepositories(USER, page, params.loadSize)
            LoadResult.Page(
                data = starredRepositories,
                prevKey = if (page == FIRST_INDEX) null else page - 1,
                nextKey = if (starredRepositories.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}
