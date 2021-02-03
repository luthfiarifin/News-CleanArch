package com.laam.news_cleanarch.core.di

import com.laam.news_cleanarch.core.data.source.remote.network.ApiService
import com.laam.news_cleanarch.core.data.source.remote.retrofit.RetrofitNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService = RetrofitNetwork
        .createNewsNetwork(RetrofitNetwork.createNewsOkHttpClient())
        .create(ApiService::class.java)
}