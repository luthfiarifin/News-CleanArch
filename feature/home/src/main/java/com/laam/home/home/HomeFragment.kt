package com.laam.home.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.laam.base.BaseFragment
import com.laam.core.presentation.model.News
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

        setUpAdapter()
    }

    private fun setUpAdapter() {
        with(viewBinding.rvTopHeadline) {
            adapter = rvTopHeadLineAdapter
        }
    }

    private fun onTopHeadlineOnClick(news: News) {
        Toast.makeText(context, news.title, Toast.LENGTH_SHORT).show()
    }
}