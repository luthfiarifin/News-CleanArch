package com.laam.news_cleanarch.search

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.laam.news_cleanarch.base.BaseViewModel
import com.laam.news_cleanarch.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/10/2021.
 */
class NewsSearchViewModel @ViewModelInject constructor(
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)

    private var page: Int = 1
    var qSearch: String = ""

    fun newsEverything() = newsUseCase.getSearch(page, qSearch).asLiveData()
}