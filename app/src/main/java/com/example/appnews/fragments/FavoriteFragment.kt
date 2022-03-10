package com.example.appnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.adapter.NewsAdapter
import com.example.appnews.database.ArticleDatabase
import com.example.appnews.database.NewsRepository
import com.example.appnews.databinding.FragmentFavoriteBinding
import com.example.appnews.fragments.viewModel.FavoriteViewModel
import com.example.appnews.models.Article
import com.example.appnews.network.RetrofitInstance
import com.example.appnews.utils.ArticleListEvent
import com.example.appnews.utils.ArticleListState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class FavoriteFragment():BaseFragment<FavoriteViewModel,FragmentFavoriteBinding>() {



    val adapter = NewsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        observer()
      viewModel.listArticles(ArticleListEvent.Fetch)
        setupRecycleView()
        onSwipe()



        super.onViewCreated(view, savedInstanceState)
    }

    private fun onSwipe() {

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.END ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder): Boolean {

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val model= adapter.differ.currentList[position]

                    viewModel.delete(model)
                viewModel.listArticles()

                Snackbar.make(viewHolder.itemView, R.string.article_delete_successful, Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.undo),View.OnClickListener {
                        viewModel.save(model)
                        adapter.notifyDataSetChanged()
                        viewModel.listArticles()
                    })
                    .setActionTextColor(resources.getColor(R.color.yellow))
                    .show()



            }
        }

    ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.rvFavorites)
   observer()
    }



    private fun observer() {

     viewModel.getAll.observe(viewLifecycleOwner, Observer {

         if (it is ArticleListState.Success)
         {
             binding.rvFavorites.visibility=View.VISIBLE
             binding.tvNoFavortites.visibility= View.INVISIBLE
             adapter.differ.submitList(it.list.toList())
         }
         if (it is ArticleListState.Empty)
         {
             binding.rvFavorites.visibility=View.INVISIBLE
             binding.tvNoFavortites.visibility= View.VISIBLE
         }



     })

    }


    private fun setupRecycleView() {


        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
        binding.rvFavorites.setHasFixedSize(true)

        binding.rvFavorites.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.rvFavorites.adapter = adapter
        adapter.setOnClickListener(object : NewsAdapter.OnClickListener {
            override fun onClick(model: Article) {
                val action =FavoriteFragmentDirections.actionFavoriteFragmentToWebViewFragment(model)
                binding.rvFavorites.findNavController().navigate(action)

            }

        })


    }

    override fun getViewModel(): Class<FavoriteViewModel> {
       return FavoriteViewModel::class.java
    }

    override fun getRespository(): NewsRepository {
       return NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))
    }

    override fun getFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {

        return FragmentFavoriteBinding.inflate(layoutInflater,container,false)
    }





}