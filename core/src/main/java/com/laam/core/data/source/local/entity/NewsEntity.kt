package com.laam.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "content") val content: String?,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "publishedAt") val publishedAt: String,
    @ColumnInfo(name = "source") val source: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "urlToImage") val urlToImage: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val newsId: Long? = null
}