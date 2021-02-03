package com.laam.news_cleanarch.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laam.news_cleanarch.core.data.source.local.entity.NewsEntity
import com.laam.news_cleanarch.core.data.source.local.entity.NewsFavoriteEntity

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Database(entities = [NewsEntity::class, NewsFavoriteEntity::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun newsFavoriteDao(): NewsFavoriteDao

    companion object {
        const val DB_NAME = "News.db"
        const val NEWS_TABLE_NAME = "news"
        const val NEWS_FAVORITE_TABLE_NAME = "news_favorite"
    }
}