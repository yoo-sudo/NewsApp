package com.example.data.repo

import com.example.data.datasource.LocalDataSource
import com.example.data.util.NewsMapper
import com.example.domain.model.News
import com.example.domain.repo.BookmarkedNewsRepository
import kotlinx.coroutines.flow.*

class BookmarkedNewsNewsRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val bookmarkedNewsMapper: NewsMapper
): BookmarkedNewsRepository {

    override suspend fun getBookmarkedNews(): Flow<List<News>> {
        return localDataSource.getBookmarkedNews().map { bookmarkedNewsMapper.mapBookmarksToNewsList(it) }
    }

    override suspend fun insertBookmarkedNews(news: News) {
        val bookmarkedNews = bookmarkedNewsMapper.mapToBookmarkedNews(news)
        localDataSource.bookmarkSelectedNews(bookmarkedNews)
    }

    override suspend fun deleteBookmarkedNewsByTitle(title: String) {
        localDataSource.deleteSelectedNews(title)
    }
}