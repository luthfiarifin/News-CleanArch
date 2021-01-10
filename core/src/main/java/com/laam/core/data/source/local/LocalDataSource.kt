package com.laam.core.data.source.local

import com.laam.core.data.source.local.entity.NewsEntity
import com.laam.core.data.source.local.room.NewsDao
import com.laam.core.data.source.remote.response.ArticleResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by luthfiarifin on 1/9/2021.
 */
@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

    suspend fun insertNews(news: List<NewsEntity>) = newsDao.insertNews(news)
}