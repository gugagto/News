package com.example.appnews.fragments.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.database.ArticleDatabase
import com.example.appnews.database.NewsRepository
import com.example.appnews.models.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel constructor(private val mRepository: NewsRepository):ViewModel() {

private val _getAll = MutableLiveData<NewsResponse>()
        var getAll: LiveData<NewsResponse> = _getAll



fun listArticles()
{
   viewModelScope.launch {

       _getAll.value= mRepository.getAllRemote().body()
   }


}





}