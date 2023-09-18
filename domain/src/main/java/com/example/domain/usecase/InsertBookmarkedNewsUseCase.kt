package com.example.domain.usecase

import com.example.domain.model.News
import com.example.domain.repo.BookmarkedNewsRepository
import javax.inject.Inject

class InsertBookmarkedNewsUseCase @Inject constructor(
    private val repository: BookmarkedNewsRepository
) {

    suspend fun execute(news: News){
        repository.insertBookmarkedNews(news)
    }

}