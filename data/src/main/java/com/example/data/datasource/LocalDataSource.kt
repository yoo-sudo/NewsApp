package com.example.data.datasource

import com.example.data.room.BookMarkDao
import com.example.data.room.entity.BookmarkedNews
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: BookMarkDao
) {

    suspend fun bookmarkSelectedNews(bookmarkedNews: BookmarkedNews) = dao.bookmarkSelectedNews(bookmarkedNews)

    fun getBookmarkedNews() = dao.getBookmarkedNews()

    suspend fun deleteSelectedNews(title:String) = dao.delectSelectedNews(title)
}