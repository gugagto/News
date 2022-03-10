package com.example.appnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.appnews.database.NewsRepository
import com.example.appnews.databinding.FragmentHomeBinding
import com.example.appnews.utils.ViewModelFactory

abstract class BaseFragment<VM:ViewModel, VB:ViewBinding>:Fragment() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= getFragment(inflater,container)

        val factory = ViewModelFactory(getRespository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel())
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getRespository(): NewsRepository

    abstract fun getFragment(inflater: LayoutInflater, container: ViewGroup?): VB



}