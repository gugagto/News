package com.example.appnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.adapter.NewsAdapter
import com.example.appnews.database.ArticleDatabase
import com.example.appnews.database.NewsRepository
import com.example.appnews.databinding.FragmentHomeBinding
import com.example.appnews.fragments.viewModel.HomeViewModel
import com.example.appnews.models.Article
import com.example.appnews.network.RetrofitInstance
import com.example.appnews.utils.InternetCheck

class HomeFragment() : BaseFragment<HomeViewModel, FragmentHomeBinding>() {




    val adapter = NewsAdapter()
    val hasInternet = InternetCheck()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        if (hasInternet.checkForInternetConnection(requireContext()))
        {
            binding.pbMain.visibility = View.VISIBLE
            viewModel.listArticles()
            observer()
            setupRecycleView()
        }
        else
        {
            Toast.makeText(requireContext(),"Verifique sua conexão com a internet",Toast.LENGTH_SHORT).show()
        }




        super.onViewCreated(view, savedInstanceState)
    }


    private fun observer() {


            viewModel.getAll.observe(viewLifecycleOwner, Observer {


                adapter.differ.submitList(it.articles.toList())
                binding.pbMain.visibility = View.INVISIBLE

            })

        }



    private fun setupRecycleView() {


        binding.rvMain.layoutManager = LinearLayoutManager(context)
        binding.rvMain.setHasFixedSize(true)

        binding.rvMain.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvMain.adapter = adapter
        adapter.setOnClickListener(object : NewsAdapter.OnClickListener {
            override fun onClick(model: Article) {
                val action = HomeFragmentDirections.actionHomeFragmentToWebViewFragment(model)
                binding.rvMain.findNavController().navigate(action)

            }

        })


    }

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getRespository(): NewsRepository {
        return NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))
    }

    override fun getFragment(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, container, false)
    }


}