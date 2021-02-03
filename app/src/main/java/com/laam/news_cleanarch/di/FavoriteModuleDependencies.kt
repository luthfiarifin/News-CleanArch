package com.laam.news_cleanarch.di

import com.laam.news_cleanarch.core.domain.usecase.NewsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by luthfiarifin on 1/18/2021.
 */
@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {

    fun provideNewsUseCase(): NewsUseCase
}