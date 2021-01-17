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
import com.laam.base.ext.SnackBarUtil.showSnackBar
import com.laam.core.domain.model.NewsFavoriteDomain
import com.laam.core.presentation.model.News
import com.laam.core.utils.DataMapper.toNewsFavoriteDomain
import com.laam.detail.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/16/2021.
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_detail

    private lateinit var itemFavorite: MenuItem

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpViewBinding()
        setUpVariable()
        setUpWebView()
        observeIsNewsFavorite()
    }

    private fun observeIsNewsFavorite() {
        viewModel.isNewsFavorite.observe(viewLifecycleOwner) { setIconFavorite(it) }
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
        arguments?.let {
            val news = it.getParcelable<News>("news")
            viewModel.news = news
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        with(viewBinding.webView) {
            viewModel.news?.let { loadUrl(it.url) }

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

    private fun setIconFavorite(isFavorite: Boolean?) {
        activity?.let {
            itemFavorite.icon = if (isFavorite == true) {
                ContextCompat.getDrawable(it, R.drawable.ic_favorite_active)
            } else {
                ContextCompat.getDrawable(it, R.drawable.ic_favorite_not_active)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        itemFavorite = menu.findItem(R.id.item_favorite)
        getIsNewsFavorite()
    }

    private fun getIsNewsFavorite() {
        viewModel.getIsNewsFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_favorite -> {
                setOnFavoriteClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setOnFavoriteClick() {
        if (viewModel.isNewsFavorite.value == false) {
            viewModel.news?.toNewsFavoriteDomain()?.let { insertNewsFavorite(it) }
        } else {
            deleteNewsFavorite()
        }
    }

    private fun deleteNewsFavorite() {
        viewModel.deleteNewsFavorite().observe(viewLifecycleOwner) {
            view?.showSnackBar("Delete to favorite success")
            viewModel.setIsNewsFavorite(false)
        }
    }

    private fun insertNewsFavorite(it: NewsFavoriteDomain) {
        viewModel.insertNewsFavorite(it).observe(viewLifecycleOwner) {
            view?.showSnackBar("Add to favorite success")
            viewModel.setIsNewsFavorite(true)
        }
    }
}