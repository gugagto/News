package com.example.appnews.database

import android.content.Context
import com.example.appnews.models.Article
import com.example.appnews.network.RetrofitInstance
import com.example.appnews.presenter.FavoriteHome
import com.example.appnews.presenter.NewsHome
import com.example.appnews.presenter.SearchHome
import kotlinx.coroutines.*

class NewsDataSource    (context: Context) {

//
//
// //   private val mRepository = NewsRepository(context)
//
//    fun getAll(callback: NewsHome.Presenter) {
//        GlobalScope.launch(Dispatchers.Main) {
//            val response = RetrofitInstance.api.getBreakingNews("br")
//            if (response.isSuccessful) {
//                response.body()?.let { callback.onSuccess(it) }
//                callback.onComplete()
//            } else {
//                callback.onError(response.message())
//                callback.onComplete()
//            }
//
//        }
//
//
//    }
//
//    fun getResearch(q:String, callback: SearchHome.Presenter)
//    {
//    GlobalScope.launch(Dispatchers.Main) {
//
//          val response=  RetrofitInstance.api.searchNews(q)
//        if (response.isSuccessful)
//        {
//            response.body()?.let { callback.onSuccess(it) }
//            callback.onComplete()
//        }
//        else
//        {
//            callback.onError(response.message())
//            callback.onComplete()
//        }
//
//
//    }
//    }
//
//
//    fun saveArticle(article: Article)
//    {
//        GlobalScope.launch(Dispatchers.Main) {
//            mRepository.updateInsert(article)
//        }
//
//
//    }
//
//    fun getAll(callback: FavoriteHome.Presenter)
//    {
//
//         var list:List<Article>
//        CoroutineScope(Dispatchers.IO).launch {
//
//         list=mRepository.getAll()
//
//            withContext(Dispatchers.Main){
//                callback.onSuccess(list)
//            }
//
//
//        }
//
//
//    }
//
//
//    fun delete(article: Article)
//    {
//        GlobalScope.launch(Dispatchers.Main) {
//            mRepository.delete(article)
//        }
//
//
//    }


}