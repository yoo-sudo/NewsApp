package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.News
import com.example.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsListUseCase @Inject constructor(
    private val repository: NewsRepo
) {

    suspend fun getPagedNewsData(country:String):Flow<PagingData<News>>{
        return repository.getNewsList(country)
    }

}