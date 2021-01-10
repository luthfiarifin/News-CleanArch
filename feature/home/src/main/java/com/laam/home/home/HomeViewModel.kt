package com.laam.home.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.laam.base.BaseViewModel
import com.laam.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/8/2021.
 */
class HomeViewModel @ViewModelInject constructor(
    newsUseCase: NewsUseCase
) : BaseViewModel() {

    val newsTopHeadLine = newsUseCase.getTopHeadline().asLiveData()
}