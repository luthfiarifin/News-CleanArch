package com.laam.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.laam.core.data.source.local.entity.NewsEntity

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase()