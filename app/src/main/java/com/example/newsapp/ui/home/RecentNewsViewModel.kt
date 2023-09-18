package com.example.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.domain.model.News
import com.example.domain.usecase.DeleteBookmarksUseCase
import com.example.domain.usecase.GetNewsListUseCase
import com.example.domain.usecase.InsertBookmarkedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentNewsViewModel @Inject constructor(
    private var getNewsListUseCase: GetNewsListUseCase,
    private val insertBookmarkedNewsUseCase: InsertBookmarkedNewsUseCase,
    private val deleteBookmarkedNewsByTitleUseCase: DeleteBookmarksUseCase
) : ViewModel() {

    private val _news = MutableLiveData<PagingData<News>>()
    val news: LiveData<PagingData<News>> get() = _news

    fun getTrendingNews(country: String) = viewModelScope.launch {
        Log.d("TAG", "Fetch News")
        getNewsListUseCase.getPagedNewsData(country).onEach {
            _news.value = it
        }.launchIn(viewModelScope)
    }

    fun addNewsToBookmark(news: News) = viewModelScope.launch {
        insertBookmarkedNewsUseCase.execute(news)
    }

    fun removeBookmark(title:String) = viewModelScope.launch {
        deleteBookmarkedNewsByTitleUseCase.execute(title)
    }
}