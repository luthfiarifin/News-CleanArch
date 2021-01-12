package com.laam.news_cleanarch.di

import com.laam.core.domain.usecase.NewsInteractor
import com.laam.core.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by luthfiarifin on 1/10/2021.
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideNewsUseCase(newsInteractor: NewsInteractor): NewsUseCase
}