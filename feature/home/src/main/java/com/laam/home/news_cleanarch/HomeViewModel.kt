package com.laam.home.news_cleanarch

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.laam.base.news_cleanarch.BaseViewModel
import com.laam.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/8/2021.
 */
class HomeViewModel @ViewModelInject constructor(
    newsUseCase: NewsUseCase
) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)

    val newsTopHeadLine = newsUseCase.getTopHeadline().asLiveData()
}