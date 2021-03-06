package com.laam.news_cleanarch.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by luthfiarifin on 1/7/2021.
 */
data class SourceResponse(
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("name") val name: String
)