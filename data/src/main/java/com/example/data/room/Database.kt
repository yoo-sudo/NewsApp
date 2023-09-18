package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.entity.BookmarkedNews


@Database(
    entities = [BookmarkedNews::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun getBookmarkedDao(): BookMarkDao
}