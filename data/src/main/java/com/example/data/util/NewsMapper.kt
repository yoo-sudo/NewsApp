package com.example.data.util

import com.example.domain.model.News
import com.example.data.response.Article
import com.example.data.room.entity.BookmarkedNews
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    fun mapResponseToNewsList(responseList: List<Article>):List<News>{
        return responseList.map { mapToNews(it) }
    }

    private fun mapToNews(article: Article): News {
        return News(
            source = article.source?.name,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt,
            content = article.content,
            isBookmarked = article.isBookmarked
        )
    }


    private fun mapToNews(bookmarkedNews: BookmarkedNews):News{
        return News(
            source = bookmarkedNews.source,
            title = bookmarkedNews.title,
            author = bookmarkedNews.author,
            publishedAt = bookmarkedNews.publishedAt,
            content = bookmarkedNews.content,
            urlToImage = bookmarkedNews.imageUrl,
            url = bookmarkedNews.url
        )
    }

    fun mapBookmarksToNewsList(list:List<BookmarkedNews>):List<News>{
        return list.map { mapToNews(it) }
    }

    fun mapToBookmarkedNews(news:News):BookmarkedNews{
        return BookmarkedNews(
            source = news.source,
            title = news.title,
            author = news.author,
            publishedAt = news.publishedAt,
            content = news.content,
            imageUrl = news.urlToImage,
            url = news.url
        )
    }
}