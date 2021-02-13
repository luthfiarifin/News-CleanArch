package com.laam.news_cleanarch.core.data.source.remote.retrofit

import com.laam.news_cleanarch.core.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by luthfiarifin on 1/7/2021.
 */
object RetrofitNetwork {

    fun createNewsOkHttpClient(): OkHttpClient {
        val hostname = BuildConfig.NEWS_API_HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/LAlZB272xQABCgeTFXzq0MuyQTFpu4lb7LOBjVoJdrE=")
            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(hostname, "sha256/c5XTqkOxoXqc60M3HuT9fgmfeexiP2+Q8BD3+6abZYU=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { addApiKeyQueryParam(it) }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    fun createNewsNetwork(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun addApiKeyQueryParam(it: Interceptor.Chain): okhttp3.Response {
        var request = it.request()

        val url = request.url
            .newBuilder()
            .addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
            .build()

        request = request.newBuilder()
            .url(url)
            .build()

        return it.proceed(request)
    }
}