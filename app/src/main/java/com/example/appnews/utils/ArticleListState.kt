package com.example.appnews.utils

import com.example.appnews.models.Article

sealed class ArticleListState{

    data class Success(val list: List<Article>) : ArticleListState()
    data class ErrorMessage(val errorMessage: String) : ArticleListState()
    object Loading : ArticleListState()
    object Empty : ArticleListState()
}





