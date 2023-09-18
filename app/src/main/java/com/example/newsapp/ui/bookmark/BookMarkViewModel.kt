package com.example.newsapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.News
import com.example.domain.usecase.DeleteBookmarksUseCase
import com.example.domain.usecase.GetBookmarkedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val getBookmarkedNewsUseCase: GetBookmarkedNewsUseCase,
    private val deleteBookmarksUseCase: DeleteBookmarksUseCase
) : ViewModel() {

    private val _bookmarkedNews = MutableLiveData<List<News>>()
    val bookmarkedNews: LiveData<List<News>> get() = _bookmarkedNews

    fun getBookmarkedNews() = viewModelScope.launch {
        getBookmarkedNewsUseCase.execute().onEach {
            _bookmarkedNews.value = it
        }.launchIn(viewModelScope)
    }

    fun removeBookmark(title: String) = viewModelScope.launch {
        deleteBookmarksUseCase.execute(title)
    }
}