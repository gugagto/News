package com.example.appnews.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.database.NewsRepository
import com.example.appnews.models.Article
import kotlinx.coroutines.launch

class WebViewModel constructor (private val mRepository: NewsRepository):ViewModel() {


fun saveToFavorites(article: Article)
{
    viewModelScope.launch {


        mRepository.updateInsert(article)


    }



}





}