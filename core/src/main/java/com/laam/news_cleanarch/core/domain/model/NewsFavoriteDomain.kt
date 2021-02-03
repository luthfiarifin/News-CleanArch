package com.laam.news_cleanarch.core.domain.model

/**
 * Created by luthfiarifin on 1/17/2021.
 */
data class NewsFavoriteDomain(
    val url: String,
    val content: String?,
    val publishedAt: String,
    val source: String?,
    val title: String,
    val urlToImage: String
)