package com.example.appnews.utils

import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QueryTextListener(lifecycle: Lifecycle,
                        private val utilTextListener: (String?)-> Unit):SearchView.OnQueryTextListener,LifecycleObserver

{


    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null




    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {


        searchJob?.cancel()
        searchJob= coroutineScope.launch {

           delay(500L)
            utilTextListener(p0)


        }
        return false
    }


}