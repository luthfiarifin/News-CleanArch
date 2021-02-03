package com.laam.news_cleanarch.core.di

import com.laam.news_cleanarch.core.data.NewsRepository
import com.laam.news_cleanarch.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by luthfiarifin on 1/10/2021.
 */

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(newsRepository: NewsRepository): INewsRepository
}