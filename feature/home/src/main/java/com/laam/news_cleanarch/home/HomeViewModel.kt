package com.laam.news_cleanarch.home

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.laam.news_cleanarch.base.BaseViewModel
import com.laam.news_cleanarch.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/8/2021.
 */
class HomeViewModel @ViewModelInject constructor(
    newsUseCase: NewsUseCase
) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)

    val newsTopHeadLine = newsUseCase.getTopHeadline().asLiveData()
}