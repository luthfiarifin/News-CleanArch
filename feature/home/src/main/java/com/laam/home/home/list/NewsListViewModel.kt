package com.laam.home.home.list

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.laam.base.BaseViewModel
import com.laam.core.domain.usecase.NewsUseCase

/**
 * Created by luthfiarifin on 1/10/2021.
 */
class NewsListViewModel @ViewModelInject constructor(
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)

    var page: Int = 1
    var category: String = ""

    fun newsEverything() = newsUseCase.getSearch(page, category).asLiveData()
}