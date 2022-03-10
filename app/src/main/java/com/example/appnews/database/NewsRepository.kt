package com.example.appnews.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.appnews.models.Article
import com.example.appnews.network.NewsApi

class NewsRepository( private val api: NewsApi,private val db:ArticleDatabase) {






    suspend fun updateInsert(article: Article) = db.getArticleDao().insert(article)

    fun getAll():LiveData<List<Article>> = db.getArticleDao().getAll()

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)


    suspend fun getAllRemote() = api.getBreakingNews()

    suspend fun search(str: String) = api.searchNews(str)


}