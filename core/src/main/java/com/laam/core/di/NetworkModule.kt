package com.laam.core.di

import com.laam.core.data.source.remote.retrofit.RetrofitNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return RetrofitNetwork.createNewsOkHttpClient()
    }
}