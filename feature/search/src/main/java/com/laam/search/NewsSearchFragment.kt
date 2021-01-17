package com.laam.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.laam.base.BaseFragment
import com.laam.base.adapter.NewsListAdapter
import com.laam.core.data.Resource
import com.laam.core.presentation.model.News
import com.laam.core.utils.DataMapper.mapToNews
import com.laam.search.databinding.FragmentNewsSearchBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/12/2021.
 */
@AndroidEntryPoint
class NewsSearchFragment : BaseFragment<FragmentNewsSearchBinding, NewsSearchViewModel>() {

    override fun getViewModelClass(): Class<NewsSearchViewModel> = NewsSearchViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_news_search

    private val rvListAdapter by lazy {
        NewsListAdapter { news ->
            onNewsListClick(news)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpBinding()
        setUpAdapter()
        setUpSearch()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            title = context?.getString(R.string.search)

            setHasOptionsMenu(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpSearch() {
        viewBinding.searchView.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                viewBinding.searchView.clearFocus()
                onSearch()
                true
            } else false
        }
    }

    private fun onSearch() {
        viewModel.qSearch = viewBinding.searchView.text.toString()
        observeNews()
    }

    private fun setUpBinding() {
        viewBinding.viewModel = viewModel
    }

    private fun setUpAdapter() {
        with(viewBinding.rvList) {
            adapter = rvListAdapter
        }
    }

    private fun observeNews() {
        viewModel.newsEverything().observe(viewLifecycleOwner, { news ->
            when (news) {
                is Resource.Loading -> {
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