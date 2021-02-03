package com.laam.news_cleanarch.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.laam.news_cleanarch.core.domain.usecase.NewsUseCase
import com.laam.news_cleanarch.favorite.FavoriteListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val newsUseCase: NewsUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteListViewModel::class.java) -> {
                FavoriteListViewModel(newsUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}