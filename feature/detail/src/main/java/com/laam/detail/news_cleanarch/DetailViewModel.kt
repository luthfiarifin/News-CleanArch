package com.laam.detail.news_cleanarch

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.laam.base.BaseViewModel
import com.laam.core.domain.model.NewsFavoriteDomain
import com.laam.core.domain.usecase.NewsUseCase
import com.laam.core.presentation.model.News
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by luthfiarifin on 1/16/2021.
 */
class DetailViewModel @ViewModelInject constructor(
    private val newsUseCase: NewsUseCase
) : BaseViewModel() {

    val isLoading = ObservableBoolean(true)
    var news: News? = null

    private val _isNewsFavorite = MutableLiveData<Boolean>()
    val isNewsFavorite
        get() = _isNewsFavorite

    fun insertNewsFavorite(newsFavoriteDomain: NewsFavoriteDomain) =
        newsUseCase.insertNewsFavorite(newsFavoriteDomain).asLiveData()

    fun deleteNewsFavorite() =
        newsUseCase.deleteNewsFavorite(news?.url ?: "").asLiveData()

    fun getIsNewsFavorite() {
        viewModelScope.launch {
            newsUseCase.isNewsFavorite(news?.url ?: "").collect { _isNewsFavorite.postValue(it) }
        }
    }

    fun setIsNewsFavorite(isFavorite: Boolean) {
        _isNewsFavorite.value = isFavorite
    }
}