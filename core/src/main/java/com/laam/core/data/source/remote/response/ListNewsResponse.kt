package com.laam.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by luthfiarifin on 1/7/2021.
 */
data class ListNewsResponse(
    @field:SerializedName("articles") val articles: List<ArticleResponse>,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("totalResults") val totalResults: Int
)