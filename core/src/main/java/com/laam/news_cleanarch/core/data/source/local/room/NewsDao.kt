package com.laam.news_cleanarch.core.data.source.local.room

import androidx.room.*
import com.laam.news_cleanarch.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by luthfiarifin on 1/9/2021.
 */
@Dao
interface NewsDao {

    @Query("SELECT * FROM ${NewsDatabase.NEWS_TABLE_NAME} WHERE qSearch = :qSearch ORDER BY publishedAt DESC")
    fun getAllNews(qSearch: String): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsEntity: List<NewsEntity>)
}