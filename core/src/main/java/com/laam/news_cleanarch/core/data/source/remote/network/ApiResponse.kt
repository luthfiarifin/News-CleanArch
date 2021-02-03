package com.laam.news_cleanarch.core.data.source.remote.network

/**
 * Created by luthfiarifin on 1/7/2021.
 */
sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Error(val errorMessage: String) : ApiResponse<Nothing>()

    object Empty : ApiResponse<Nothing>()
}