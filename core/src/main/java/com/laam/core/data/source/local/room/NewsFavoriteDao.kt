package com.laam.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Query
import com.laam.core.data.source.local.entity.NewsFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by luthfiarifin on 1/16/2021.
 */
@Dao
interface NewsFavoriteDao {

    @Query("SELECT * FROM ${NewsDatabase.NEWS_FAVORITE_TABLE_NAME} WHERE url = :url")
    fun getNewsFavorite(url: String): Flow<NewsFavoriteEntity?>
}