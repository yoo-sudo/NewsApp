package com.example.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.domain.model.News
import com.example.data.util.NewsMapper
import kotlinx.coroutines.flow.first
import java.lang.Exception

private const val NEWS_STARTING_PAGE_INDEX = 1

class TrendingNewsPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val country: String,
    private val mapper: NewsMapper,
    private val localDataSource: LocalDataSource,
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {

        val position = params.key ?: NEWS_STARTING_PAGE_INDEX
        return try {
            val response = remoteDataSource.getTrendingNews(country, position)
            val nextKey = if (response.articles.isEmpty()) {
                null
            } else {
                position + 1
            }

            val articles = response.articles

            Log.d("NEWS", "loadDebug: before ${articles.map { it.isBookmarked }}")

            val bookmarks = localDataSource.getBookmarkedNews().first()

            articles.forEach {
                if (it.title in bookmarks.map { it.title }) {
                    it.isBookmarked = true
                }
            }

            Log.d("BOOKMARKDEBUG", "load: ${bookmarks.map { it.title }}")

            Log.d("TAG", "loadDebug: ${articles.map { it.isBookmarked }}")

            LoadResult.Page(
                data = mapper.mapResponseToNewsList(articles),
                prevKey = if (position == NEWS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}