package com.example.newsapp.di

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.data.repo.BookmarkedNewsNewsRepositoryImpl
import com.example.data.repo.NewsRepoImpl
import com.example.data.util.NewsMapper
import com.example.domain.repo.BookmarkedNewsRepository
import com.example.domain.repo.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        mapper: NewsMapper,
    ): NewsRepo {
        return NewsRepoImpl(remoteDataSource, localDataSource, mapper)
    }

    @Provides
    @Singleton
    fun provideBookmarkedNewsRepository(localDataSource: LocalDataSource, mapper: NewsMapper): BookmarkedNewsRepository {
        return BookmarkedNewsNewsRepositoryImpl(localDataSource, mapper)
    }
}