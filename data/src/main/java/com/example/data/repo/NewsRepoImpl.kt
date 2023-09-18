package com.example.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.data.pagingsource.TrendingNewsPagingSource
import com.example.data.util.NewsMapper
import com.example.domain.model.News
import com.example.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val newsMapper: NewsMapper,
) : NewsRepo {

    override suspend fun getNewsList(country: String): Flow<PagingData<News>> {

        return Pager(config = PagingConfig(
            pageSize = TrendingNewsPagingSource.NETWORK_PAGE_SIZE, enablePlaceholders = false
        ), pagingSourceFactory = {
            TrendingNewsPagingSource(remoteDataSource, country, newsMapper, localDataSource)
        }).flow
    }
}