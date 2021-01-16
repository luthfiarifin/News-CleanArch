package com.laam.detail

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.laam.base.BaseViewModel
import com.laam.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/16/2021.
 */
class DetailViewModel @ViewModelInject constructor(
    newsUseCase: NewsUseCase
) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)
    var newsUrl: String? = null

    val isNewsFavorite = newsUseCase.isNewsFavorite(newsUrl ?: "").asLiveData()
}