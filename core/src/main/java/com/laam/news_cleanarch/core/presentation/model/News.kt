package com.laam.news_cleanarch.core.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by luthfiarifin on 1/10/2021.
 */
@Parcelize
data class News(
    val url: String,
    val content: String?,
    val publishedAt: String,
    val source: String?,
    val title: String,
    val urlToImage: String?
) : Parcelable