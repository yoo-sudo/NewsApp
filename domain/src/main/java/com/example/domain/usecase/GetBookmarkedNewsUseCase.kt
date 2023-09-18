package com.example.domain.usecase

import com.example.domain.model.News
import com.example.domain.repo.BookmarkedNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedNewsUseCase @Inject constructor(
    private val repository: BookmarkedNewsRepository
) {

    suspend fun execute():Flow<List<News>>{
        return repository.getBookmarkedNews()
    }

}