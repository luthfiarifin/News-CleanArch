package com.laam.news_cleanarch.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.laam.news_cleanarch.base.BaseFragment
import com.laam.news_cleanarch.base.adapter.NewsListAdapter
import com.laam.news_cleanarch.core.presentation.model.News
import com.laam.news_cleanarch.core.utils.DataMapper.mapToNews
import com.laam.news_cleanarch.di.FavoriteModuleDependencies
import com.laam.news_cleanarch.favorite.databinding.FragmentFavoriteListBinding
import com.laam.news_cleanarch.favorite.di.DaggerFavoriteComponent
import com.laam.news_cleanarch.favorite.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

/**
 * Created by luthfiarifin on 1/17/2021.
 */
class FavoriteListFragment : BaseFragment<FragmentFavoriteListBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_favorite_list

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteListViewModel by viewModels { factory }

    private val rvListAdapter by lazy {
        NewsListAdapter { news ->
            onNewsListClick(news)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpInjection()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpBinding()
        setUpAdapter()
        observeNews()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            title = context?.getString(R.string.favorite_list)

            setHasOptionsMenu(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpInjection() {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun setUpBinding() {
        viewBinding?.viewModel = viewModel
    }

    private fun setUpAdapter() {
        viewBinding?.rvList?.adapter = rvListAdapter
    }

    private fun observeNews() {
        viewModel.allNewsFavorite.observe(viewLifecycleOwner) {
            val news = it.map { domain -> domain.mapToNews() }
            rvListAdapter.submitList(news)
            startShimmer(false)
            viewModel.isLoading.set(false)

            viewModel.isEmpty.set(news.isEmpty())
        }
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
        if (isStart) viewBinding?.placeHolderNewsList?.shimmer?.startShimmer()
        else viewBinding?.placeHolderNewsList?.shimmer?.stopShimmer()
    }

    private fun navigateToDetailFragment(news: News) {
        val bundle = bundleOf("news" to news)
        findNavController().navigate(com.laam.news_cleanarch.base.R.id.detail_nav_graph, bundle)
    }

    override fun onDestroyView() {
        viewBinding?.rvList?.adapter = null
        super.onDestroyView()
    }
}