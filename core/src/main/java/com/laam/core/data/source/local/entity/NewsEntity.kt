package com.laam.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.laam.core.data.source.local.room.NewsDatabase

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Entity(tableName = NewsDatabase.NEWS_COLUMN_NAME)
data class NewsEntity(
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "publishedAt") var publishedAt: String,
    @ColumnInfo(name = "source") var source: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "urlToImage") var urlToImage: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var newsId: Long? = null
}