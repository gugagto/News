package com.example.appnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.adapter.NewsAdapter
import com.example.appnews.database.ArticleDatabase
import com.example.appnews.database.NewsRepository
import com.example.appnews.databinding.FragmentSearchBinding
import com.example.appnews.fragments.viewModel.SearchViewModel
import com.example.appnews.models.Article
import com.example.appnews.network.RetrofitInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment :BaseFragment<SearchViewModel,FragmentSearchBinding>(){


    val adapter = NewsAdapter()
    private var searchJob: Job? = null
    private val coroutineScope = lifecycle.coroutineScope


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        setupRecycleView()
        search()
        observer()





        super.onViewCreated(view, savedInstanceState)
    }




    private fun observer() {
      viewModel.getSearch.observe(viewLifecycleOwner, Observer {


              adapter.differ.submitList(it.articles.toList())




      })
    }


    private fun search() {

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                binding.pbSearch.visibility=View.VISIBLE
                searchJob?.cancel()
                searchJob = coroutineScope.launch {
                    delay(500L)
                    if (p0 != null) {
                        binding.pbSearch.visibility=View.INVISIBLE
                        viewModel.search(p0)
                    }

                }

                return false
            }


        })


    }



    private fun setupRecycleView() {


        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.setHasFixedSize(true)

        binding.rvSearch.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvSearch.adapter = adapter
        adapter.setOnClickListener(object : NewsAdapter.OnClickListener {
            override fun onClick(model: Article) {
                val action = SearchFragmentDirections.actionSearchFragmentToWebViewFragment(model)
                binding.rvSearch.findNavController().navigate(action)

            }

        })


    }


    override fun getViewModel(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun getRespository(): NewsRepository {
       return  NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))
    }

    override fun getFragment(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding {

        return FragmentSearchBinding.inflate(layoutInflater,container,false)

    }


}