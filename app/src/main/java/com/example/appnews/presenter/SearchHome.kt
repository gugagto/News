package com.example.appnews.presenter

import com.example.appnews.models.NewsResponse

interface SearchHome {

    interface Presenter{

        fun search(q:String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(msg: String)
        fun onComplete()



    }


}