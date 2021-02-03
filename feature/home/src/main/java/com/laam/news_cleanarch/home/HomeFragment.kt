package com.laam.news_cleanarch.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.laam.news_cleanarch.base.BaseFragmentVm
import com.laam.news_cleanarch.core.data.Resource
import com.laam.news_cleanarch.core.presentation.model.News
import com.laam.news_cleanarch.core.utils.DataMapper.mapToNews
import com.laam.news_cleanarch.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/8/2021.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragmentVm<FragmentHomeBinding, HomeViewModel>() {

    private val rvTopHeadLineAdapter by lazy {
        TopHeadlineAdapter { news ->
            onTopHeadlineOnClick(news)
        }
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpBinding()
        setUpAdapter()
        observeTopHeadline()
        setUpViewPager()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            title = context?.getString(R.string.app_name)

            setHasOptionsMenu(true)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }

    private fun setUpBinding() {
        viewBinding.viewModel = viewModel
    }

    private fun setUpAdapter() {
        with(viewBinding.rvTopHeadline) {
            adapter = rvTopHeadLineAdapter
        }
    }

    private fun setUpViewPager() {
        val categoryList = listOf(
            "Kesehatan",
            "Olahraga",
            "Otomotif",
            "Teknologi",
            "Makanan"
        )

        viewBinding.viewPager.adapter = activity?.let { HomePagerAdapter(it, categoryList) }
        setUpTabMediator(categoryList)
    }

    private fun setUpTabMediator(categoryList: List<String>) {
        TabLayoutMediator(
            viewBinding.tabLayout, viewBinding.viewPager
        ) { tab, position -> tab.text = categoryList[position] }.attach()
    }

    private fun onTopHeadlineOnClick(news: News) {
        navigateToDetailFragment(news)
    }

    private fun observeTopHeadline() {
        viewModel.newsTopHeadLine.observe(viewLifecycleOwner, { news ->
            when (news) {
                is Resource.Loading -> {
                    viewModel.isLoading.set(true)
                }
                is Resource.Success -> {
                    val newsList = news.data?.map { it.mapToNews() }
                    rvTopHeadLineAdapter.submitList(newsList)
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

    override fun onResume() {
        super.onResume()
        startShimmer()
    }

    override fun onPause() {
        startShimmer(false)
        super.onPause()
    }

    private fun startShimmer(isStart: Boolean = true) {
        if (isStart) viewBinding.placeholderTopHeadline.shimmer.startShimmer()
        else viewBinding.placeholderTopHeadline.shimmer.stopShimmer()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchMenu -> {
                navigateToSearchFragment()
                true
            }
            R.id.favoriteMenu -> {
                navigateToFavoriteFragment()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun navigateToFavoriteFragment() {
        findNavController().navigate(R.id.includedGraph)
    }

    private fun navigateToSearchFragment() {
        findNavController().navigate(R.id.search_nav_graph)
    }

    private fun navigateToDetailFragment(news: News) {
        val bundle = bundleOf("news" to news)
        findNavController().navigate(R.id.detail_nav_graph, bundle)
    }
}