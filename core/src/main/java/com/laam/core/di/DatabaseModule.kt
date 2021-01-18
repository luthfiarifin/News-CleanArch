package com.laam.core.di

import android.content.Context
import androidx.room.Room
import com.laam.core.data.source.local.room.NewsDao
import com.laam.core.data.source.local.room.NewsDatabase
import com.laam.core.data.source.local.room.NewsFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by luthfiarifin on 1/7/2021.
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        NewsDatabase.DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()

    @Provides
    fun provideNewsFavoriteDao(database: NewsDatabase): NewsFavoriteDao = database.newsFavoriteDao()
}