package com.laam.news_cleanarch.di

import com.laam.news_cleanarch.core.domain.usecase.NewsInteractor
import com.laam.news_cleanarch.core.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by luthfiarifin on 1/10/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideNewsUseCase(newsInteractor: NewsInteractor): NewsUseCase
}