package com.example.appnews.presenter

import com.example.appnews.models.NewsResponse
import com.example.appnews.database.NewsDataSource

class NewsPresenter(val view: ViewHome.View,private val dataSource: NewsDataSource){

//    override fun requestAll() {
//       this.view.showProgressBar()
//        dataSource.getAll(this)
//    }
//
//    override fun onSuccess(newsResponse: NewsResponse) {
//      this.view.showArticles(newsResponse.articles)
//    }
//
//    override fun onError(msg: String) {
//      this.view.showFailure(msg)
//    }
//
//    override fun onComplete() {
//        this.view.hideProgressBar()
//    }
}