package com.laam.home.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.tabs.TabLayoutMediator
import com.laam.base.BaseFragment
import com.laam.core.data.Resource
import com.laam.core.presentation.model.News
import com.laam.core.utils.DataMapper.mapToNews
import com.laam.home.R
import com.laam.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/8/2021.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val rvTopHeadLineAdapter by lazy {
        TopHeadlineAdapter { news ->
            onTopHeadlineOnClick(news)
        }
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
        setUpAdapter()
        observeTopHeadline()
        setUpViewPager()
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
        Toast.makeText(context, news.title, Toast.LENGTH_SHORT).show()
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
}