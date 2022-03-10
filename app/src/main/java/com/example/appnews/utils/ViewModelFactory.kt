package com.example.appnews.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appnews.database.NewsRepository
import com.example.appnews.fragments.viewModel.FavoriteViewModel
import com.example.appnews.fragments.viewModel.HomeViewModel
import com.example.appnews.fragments.viewModel.SearchViewModel
import com.example.appnews.fragments.viewModel.WebViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mRepository:NewsRepository):ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        return when{

            modelClass.isAssignableFrom(HomeViewModel::class.java)-> HomeViewModel(mRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java)-> SearchViewModel(mRepository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(mRepository) as T
            modelClass.isAssignableFrom(WebViewModel::class.java) -> WebViewModel(mRepository) as T
            else -> throw IllegalArgumentException("ViewModel nao encontrada")

        }
    }




}