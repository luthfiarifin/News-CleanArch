package com.laam.news_cleanarch.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by luthfiarifin on 1/7/2021.
 */
data class ArticleResponse(
   @field:SerializedName("author") val author: String?,
   @field:SerializedName("content") val content: String?,
   @field:SerializedName("description") val description: String?,
   @field:SerializedName("publishedAt") val publishedAt: String,
   @field:SerializedName("source") val source: SourceResponse,
   @field:SerializedName("title") val title: String,
   @field:SerializedName("url") val url: String,
   @field:SerializedName("urlToImage") val urlToImage: String?
)