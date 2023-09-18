package com.example.data

import TrendingNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTrendingNews(
        @Query("country") country:String,
        @Query("apiKey") apiKey:String,
        @Query("page") page:Int
    ):TrendingNewsResponse

    companion object{
        const val API_KEY = "60cb046c51254a0d8bedfa068bafbd95"
        const val BASE_URL = "https://newsapi.org/v2/"
    }

}