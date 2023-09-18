package com.example.data.datasource

import TrendingNewsResponse
import com.example.data.NewsApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val newsApi: NewsApi,
) {

    suspend fun getTrendingNews(country: String, page: Int): TrendingNewsResponse {
        return newsApi.getTrendingNews(country = country, apiKey = NewsApi.API_KEY, page = page)
    }
}