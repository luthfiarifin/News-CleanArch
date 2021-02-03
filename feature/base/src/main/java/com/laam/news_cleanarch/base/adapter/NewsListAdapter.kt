package com.laam.news_cleanarch.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.laam.core.presentation.model.News
import com.laam.news_cleanarch.base.R
import com.laam.news_cleanarch.base.databinding.ItemNewsListBinding

/**
 * Created by luthfiarifin on 1/10/2021.
 */
class NewsListAdapter(private val callback: (News) -> Unit) :
    ListAdapter<News, NewsListAdapter.ViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.data = news

            binding.root.setOnClickListener {
                callback(news)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.url == newItem.url
        }
    }
}