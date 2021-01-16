package com.laam.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.laam.base.BaseFragment
import com.laam.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/16/2021.
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpViewBinding()
        setUpVariable()
        setUpWebView()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            title = context?.getString(R.string.app_name)

            setHasOptionsMenu(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun setUpViewBinding() {
        viewBinding.viewModel = viewModel
    }

    private fun setUpVariable() {
        arguments?.let { viewModel.newsUrl = it.getString("url") }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        with(viewBinding.webView) {
            viewModel.newsUrl?.let { loadUrl(it) }

            settings.let { webSetting ->
                webSetting.javaScriptEnabled = true
                webSetting.setSupportZoom(false)
            }

            val webViewClient = object : WebViewClient() {
                override fun onPageCommitVisible(view: WebView?, url: String?) {
                    viewModel.isLoading.set(false)
                    super.onPageCommitVisible(view, url)
                }
            }
            setWebViewClient(webViewClient)
        }
    }

    private fun observeIsNewsFavorite(item: MenuItem?) {
        viewModel.isNewsFavorite.observe(viewLifecycleOwner, { isFavorite ->
            activity?.let {
                item?.icon = if (isFavorite) {
                    ContextCompat.getDrawable(it, R.drawable.ic_favorite_active)
                } else {
                    ContextCompat.getDrawable(it, R.drawable.ic_favorite_not_active)
                }
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        observeIsNewsFavorite(menu.findItem(R.id.item_favorite))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_detail, menu)
    }
}