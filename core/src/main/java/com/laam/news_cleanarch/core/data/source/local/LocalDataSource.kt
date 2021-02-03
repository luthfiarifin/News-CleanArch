package com.laam.news_cleanarch.core.data.source.local

import com.laam.news_cleanarch.core.data.source.local.entity.NewsEntity
import com.laam.news_cleanarch.core.data.source.local.entity.NewsFavoriteEntity
import com.laam.news_cleanarch.core.data.source.local.room.NewsDao
import com.laam.news_cleanarch.core.data.source.local.room.NewsFavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by luthfiarifin on 1/9/2021.
 */
@Singleton
class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao,
    private val newsFavoriteDao: NewsFavoriteDao
) {

    fun getAllNews(qSearch: String): Flow<List<NewsEntity>> = newsDao.getAllNews(qSearch)

    suspend fun insertNews(news: List<NewsEntity>) = newsDao.insertNews(news)

    fun getAllNewsFavorite(): Flow<List<NewsFavoriteEntity>> =
        newsFavoriteDao.getAllNewsFavorite()

    fun getNewsFavorite(url: String): Flow<NewsFavoriteEntity?> =
        newsFavoriteDao.getNewsFavorite(url)

    suspend fun insertNewsFavorite(newsFavorite: NewsFavoriteEntity): Long =
        newsFavoriteDao.insertNewsFavorite(newsFavorite)

    suspend fun deleteNewsFavorite(url: String): Int =
        newsFavoriteDao.deleteNewsFavorite(url)
}