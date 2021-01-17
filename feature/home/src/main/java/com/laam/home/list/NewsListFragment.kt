package com.laam.home.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.laam.base.BaseFragmentVm
import com.laam.base.adapter.NewsListAdapter
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
class NewsListFragment : BaseFragmentVm<FragmentNewsListBinding, NewsListViewModel>() {

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
                    startShimmer()
                    viewModel.isLoading.set(true)
                }
                is Resource.Success -> {
                    val newsList = news.data?.map { it.mapToNews() }
                    rvListAdapter.submitList(newsList)
                    startShimmer(false)
                    viewModel.isLoading.set(false)
                }
                is Resource.Error -> {
                    startShimmer(false)
                    viewModel.isLoading.set(false)
                }
            }
        })
    }

    private fun onNewsListClick(news: News) {
        navigateToDetailFragment(news)
    }

    override fun onResume() {
        super.onResume()
        startShimmer()
    }

    override fun onPause() {
        startShimmer(false)
        super.onPause()
    }

    private fun startShimmer(isStart: Boolean = true) {
        if (isStart) viewBinding.placeHolderNewsList.shimmer.startShimmer()
        else viewBinding.placeHolderNewsList.shimmer.stopShimmer()
    }

    private fun navigateToDetailFragment(news: News) {
        val bundle = bundleOf("news" to news)
        findNavController().navigate(R.id.detail_nav_graph, bundle)
    }
}