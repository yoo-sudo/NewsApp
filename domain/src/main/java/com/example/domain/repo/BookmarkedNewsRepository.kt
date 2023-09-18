package com.example.domain.repo

import com.example.domain.model.News
import kotlinx.coroutines.flow.Flow

interface BookmarkedNewsRepository {

    suspend fun getBookmarkedNews(): Flow<List<News>>

    suspend fun insertBookmarkedNews(news: News)

    suspend fun deleteBookmarkedNewsByTitle(title:String)
}