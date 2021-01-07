package com.laam.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by luthfiarifin on 1/7/2021.
 */
data class SourceResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)