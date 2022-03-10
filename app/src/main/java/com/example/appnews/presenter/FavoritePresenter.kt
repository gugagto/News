package com.example.appnews.presenter

import android.content.Context
import com.example.appnews.models.Article
import com.example.appnews.database.NewsDataSource

class FavoritePresenter(context: Context,val view: ViewHome.Favorite):FavoriteHome.Presenter {
    override fun onSuccess(list: List<Article>) {
        TODO("Not yet implemented")
    }

   private val data = NewsDataSource(context)
//
//    fun saveArticle(article: Article)
//    {
//        data.saveArticle(article)
//
//
//    }
//
//    override fun onSuccess(list: List<Article>) {
//        view.showArticles(list)
//
//    }
//
//
//    fun getAll()
//    {
//        data.getAll(this)
//    }
//
//    fun delete(article: Article)
//    {
//        data.delete(article)
//
//
//    }
//



}