package com.laam.news_cleanarch.favorite

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.asLiveData
import com.laam.news_cleanarch.base.BaseViewModel
import com.laam.news_cleanarch.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/17/2021.
 */
class FavoriteListViewModel(newsUseCase: NewsUseCase) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)
    val isEmpty = ObservableBoolean(false)

    val allNewsFavorite = newsUseCase.getAllNewsFavorite().asLiveData()
}