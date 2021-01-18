package com.laam.news_cleanarch.favorite

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.asLiveData
import com.laam.base.news_cleanarch.BaseViewModel
import com.laam.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/17/2021.
 */
class FavoriteListViewModel(newsUseCase: NewsUseCase) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)

    val allNewsFavorite = newsUseCase.getAllNewsFavorite().asLiveData()
}