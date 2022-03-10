package com.example.appnews.fragments.viewModel

import androidx.lifecycle.*
import com.example.appnews.database.NewsRepository
import com.example.appnews.models.Article
import com.example.appnews.utils.ArticleListEvent
import com.example.appnews.utils.ArticleListState
import kotlinx.coroutines.launch

class FavoriteViewModel constructor(private val mRepository: NewsRepository) : ViewModel() {


    private val _getAll = MutableLiveData<ArticleListEvent>()
    var getAll: LiveData<ArticleListState> = _getAll.switchMap {

        when (it) {
            ArticleListEvent.Fetch -> getLiveData()
        }


    }

    private fun getLiveData(): LiveData<ArticleListState> {
        return liveData {

            try {
                emit(ArticleListState.Loading)
                val list = mRepository.getAll().map {
                    if (it.isEmpty()) {
                        ArticleListState.Empty
                    } else {
                        ArticleListState.Success(it)
                    }
                }
                emitSource(list)

            } catch (e: Exception) {
                emit(ArticleListState.ErrorMessage("Algo deu errado!"))
            }


        }


    }

    fun listArticles(event: ArticleListEvent)
    {
        this._getAll.postValue(event)
    }


    fun delete(model: Article) {

        viewModelScope.launch {

            mRepository.delete(model)
        }


    }


    fun save(model: Article) {
        viewModelScope.launch {

            mRepository.updateInsert(model)
        }


    }

    fun listArticles() {


    }


}



