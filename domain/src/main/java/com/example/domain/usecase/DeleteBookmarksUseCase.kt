package com.example.domain.usecase

import com.example.domain.repo.BookmarkedNewsRepository
import javax.inject.Inject

class DeleteBookmarksUseCase @Inject constructor(
    private val repository: BookmarkedNewsRepository
) {

    suspend fun execute(title:String){
        repository.deleteBookmarkedNewsByTitle(title)
    }

}