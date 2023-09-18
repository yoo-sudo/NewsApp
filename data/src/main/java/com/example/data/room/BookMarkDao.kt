package com.example.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.room.entity.BookmarkedNews
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun bookmarkSelectedNews(bookmarkedNews: BookmarkedNews)

    @Query("SELECT * FROM bookmarks ORDER by publishedAt DESC")
    fun getBookmarkedNews(): Flow<List<BookmarkedNews>>

    @Query("DELETE FROM bookmarks WHERE title=:title")
    suspend fun delectSelectedNews(title:String)
}