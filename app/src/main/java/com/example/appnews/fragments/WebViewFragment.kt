package com.example.appnews.fragments

import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.appnews.R
import com.example.appnews.database.ArticleDatabase
import com.example.appnews.database.NewsRepository
import com.example.appnews.databinding.FragmentWebViewBinding
import com.example.appnews.fragments.viewModel.WebViewModel
import com.example.appnews.models.Article
import com.example.appnews.network.RetrofitInstance

class WebViewFragment:BaseFragment<WebViewModel,FragmentWebViewBinding>() {


    private lateinit var article:Article
    private val args: WebViewFragmentArgs by navArgs()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (context as AppCompatActivity).setSupportActionBar(binding.tbWebview)
        binding.tbWebview.title = ""
        binding.tbWebview.setNavigationIcon(R.drawable.ic_back)
        binding.tbWebview.setNavigationOnClickListener {
                requireActivity().onBackPressed()
        }


        setHasOptionsMenu(true)
        article= args.article
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled= true
        article.url?.let {
        binding.webView.loadUrl(it)

        }




        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_favorite,menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId== R.id.action_save_art)
        {
            viewModel.saveToFavorites(article)
            Toast.makeText(requireContext(),R.string.article_saved_successful,Toast.LENGTH_SHORT).show()


        }


        return super.onOptionsItemSelected(item)
    }

    override fun getViewModel(): Class<WebViewModel> {
       return WebViewModel::class.java
    }

    override fun getRespository(): NewsRepository {
       return NewsRepository(RetrofitInstance.api, ArticleDatabase.invoke(requireContext()))
    }

    override fun getFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebViewBinding {
       return FragmentWebViewBinding.inflate(layoutInflater,container,false)
    }
}