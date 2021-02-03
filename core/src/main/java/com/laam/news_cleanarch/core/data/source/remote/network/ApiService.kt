package com.laam.news_cleanarch.core.data.source.remote.network

import com.laam.news_cleanarch.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by luthfiarifin on 1/9/2021.
 */
interface ApiService {

    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("country") country: String?
    ): ListNewsResponse

    @GET("everything")
    suspend fun getSearch(
        @Query("page") page: Int,
        @Query("language") language: String?,
        @Query("q") q: String?
    ): ListNewsResponse
}