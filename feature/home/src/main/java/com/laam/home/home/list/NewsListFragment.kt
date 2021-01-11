package com.laam.home.home.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.laam.base.BaseFragment
import com.laam.core.data.Resource
import com.laam.core.presentation.model.News
import com.laam.core.utils.DataMapper.mapToNews
import com.laam.home.R
import com.laam.home.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/10/2021.
 */
@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding, NewsListViewModel>() {

    override fun getViewModelClass(): Class<NewsListViewModel> = NewsListViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_news_list

    private val rvListAdapter by lazy {
        NewsListAdapter { news ->
            onNewsListClick(news)
        }
    }

    companion object {
        private const val ARG_CATEGORY = "arg_category"

        fun newInstance(category: String) = NewsListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CATEGORY, category)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpVariable()
        setUpBinding()
        setUpAdapter()
        observeNews()
    }

    private fun setUpVariable() {
        arguments?.let { viewModel.category = it.getString(ARG_CATEGORY) ?: "" }
    }

    private fun setUpBinding() {
        viewBinding.viewModel = viewModel
    }

    private fun setUpAdapter() {
        with (viewBinding.rvList) {
            adapter = rvListAdapter
        }
    }

    private fun observeNews() {
        viewModel.newsEverything().observe(viewLifecycleOwner, { news ->
            when (news) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val newsList = news.data?.map { it.mapToNews() }
                    rvListAdapter.submitList(newsList)
                }
                is Resource.Error -> {

                }
            }
        })
    }

    private fun onNewsListClick(news: News) {
        Toast.makeText(context, news.title, Toast.LENGTH_SHORT).show()
    }
}