package com.example.appnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnews.adapter.NewsAdapter
import com.example.appnews.databinding.ActivityMainBinding
import com.example.appnews.models.Article
import com.example.appnews.database.NewsDataSource
import com.example.appnews.presenter.NewsPresenter
import com.example.appnews.presenter.ViewHome



class MainActivity() : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navHostFragment:NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       initViews()

    }

    private fun initViews() {

        navHostFragment= supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener(object :NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                title = destination.label
            }


        })

        binding.bottomNav.setupWithNavController(navController)



    }


}