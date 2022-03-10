package com.example.appnews.presenter

import com.example.appnews.models.NewsResponse

interface NewsHome {

    interface Presenter {

        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(msg: String)
        fun onComplete()
    }


}