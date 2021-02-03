package com.laam.news_cleanarch.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.laam.news_cleanarch.core.data.source.local.room.NewsDatabase

/**
 * Created by luthfiarifin on 1/16/2021.
 */
@Entity(tableName = NewsDatabase.NEWS_FAVORITE_TABLE_NAME)
data class NewsFavoriteEntity(
    @PrimaryKey @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "publishedAt") var publishedAt: String,
    @ColumnInfo(name = "source") var source: String?,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "urlToImage") var urlToImage: String
)