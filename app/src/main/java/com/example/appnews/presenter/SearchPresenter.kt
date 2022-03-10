package com.example.appnews.presenter

import com.example.appnews.models.NewsResponse
import com.example.appnews.database.NewsDataSource

class SearchPresenter(val view: ViewHome.View,private val dataSource: NewsDataSource)
{
//    override fun search(q: String) {
//       this.view.showProgressBar()
//            this.dataSource.getResearch(q,this)
//    }
//
//    override fun onSuccess(newsResponse: NewsResponse) {
//       this.view.showArticles(newsResponse.articles)
//    }
//
//    override fun onError(msg: String) {
//       this.view.showFailure(msg)
//    }
//
//    override fun onComplete() {
//       this.view.hideProgressBar()
//    }


}