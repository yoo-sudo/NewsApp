package com.example.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
class BookmarkedNews(
    @PrimaryKey @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "source") val source: String? = null,
    @ColumnInfo(name = "author") val author: String? = null,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String? = null,
    @ColumnInfo(name = "content") val content: String? = null,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
)