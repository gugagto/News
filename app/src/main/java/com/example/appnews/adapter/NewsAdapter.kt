package com.example.appnews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appnews.R
import com.example.appnews.databinding.ItemRowBinding
import com.example.appnews.models.Article
import kotlinx.coroutines.NonDisposableHandle.parent
import java.text.SimpleDateFormat


class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {




    private var onClickListener: OnClickListener? = null


    inner class MyViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)


    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val model = differ.currentList[position]

        val sdf=SimpleDateFormat("dd/MM/yyyy")
        val date =SimpleDateFormat("yyyy-MM-dd").parse(model.publishedAt)
        val dateFormatted = sdf.format(date)



        if (holder is MyViewHolder) {
            holder.binding.tvTitle.text = model.author ?: model.source?.name
            holder.binding.tvDescription.text = model.description
            holder.binding.tvPublishedAt.text = dateFormatted
            holder.binding.tvSource.text = model.source?.name ?: model.author
            Glide.with(holder.itemView.context)
                .load(model.urlToImage)
                .placeholder(R.drawable.g1)
                .into(holder.binding.iv)
            holder.itemView.setOnClickListener {

                if (onClickListener != null) {
                    onClickListener!!.onClick(model)
                }


            }


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(model: Article)
    }





}