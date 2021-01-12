package com.laam.core.domain.model

/**
 * Created by luthfiarifin on 1/10/2021.
 */
data class NewsDomain(
    val url: String,
    val author: String?,
    val content: String?,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val urlToImage: String
)