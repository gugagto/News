package com.example.appnews.presenter

import android.os.Message
import com.example.appnews.models.Article

interface ViewHome {



    interface View{

        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(list: List<Article>)
    }

    interface Favorite{

        fun showArticles(list : List<Article>)

    }


}