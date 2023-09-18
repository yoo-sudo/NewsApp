package com.example.domain.repo

import androidx.paging.PagingData
import com.example.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepo {

    suspend fun getNewsList(country:String): Flow<PagingData<News>>
}