package com.example.appnews.fragments.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnews.database.NewsRepository
import com.example.appnews.models.NewsResponse
import kotlinx.coroutines.launch

class SearchViewModel constructor(private val mRepository: NewsRepository):ViewModel() {

    private val _getSearch = MutableLiveData<NewsResponse>()
    var getSearch: LiveData<NewsResponse> = _getSearch


 fun search(string: String)
    {
            viewModelScope.launch {

                if (mRepository.search(string).isSuccessful)
                {
                    _getSearch.value= mRepository.search(string).body()
                }



            }


    }



}