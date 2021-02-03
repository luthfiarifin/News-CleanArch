package com.laam.news_cleanarch.core.data.source.remote

import com.laam.news_cleanarch.core.data.source.remote.network.ApiResponse
import com.laam.news_cleanarch.core.data.source.remote.network.ApiService
import com.laam.news_cleanarch.core.data.source.remote.response.ArticleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by luthfiarifin on 1/9/2021.
 */
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getTopHeadline(): Flow<ApiResponse<List<ArticleResponse>>> {
        return flow {
            try {
                val response = apiService.getTopHeadline( "id")
                val dataArray = response.articles
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearch(page: Int, q: String?): Flow<ApiResponse<List<ArticleResponse>>> {
        return flow {
            try {
                val response = apiService.getSearch(page, "id", q)
                val dataArray = response.articles
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}