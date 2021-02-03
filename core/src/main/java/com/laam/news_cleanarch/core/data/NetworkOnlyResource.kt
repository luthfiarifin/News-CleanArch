package com.laam.news_cleanarch.core.data

import com.laam.news_cleanarch.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

/**
 * Created by luthfiarifin on 1/11/2021.
 */
abstract class NetworkOnlyResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(flowOf(Resource.Success(mapToResult(apiResponse.data))))
            }
            is ApiResponse.Empty -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>("Empty result"))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun mapToResult(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}