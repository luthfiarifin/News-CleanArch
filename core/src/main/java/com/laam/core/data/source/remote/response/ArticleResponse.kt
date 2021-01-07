package com.laam.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by luthfiarifin on 1/7/2021.
 */
data class ArticleResponse(
   @SerializedName("author") val author: String,
   @SerializedName("content") val content: String?,
   @SerializedName("description") val description: String,
   @SerializedName("publishedAt") val publishedAt: String,
   @SerializedName("source") val source: SourceResponse,
   @SerializedName("title") val title: String,
   @SerializedName("url") val url: String,
   @SerializedName("urlToImage") val urlToImage: String
)