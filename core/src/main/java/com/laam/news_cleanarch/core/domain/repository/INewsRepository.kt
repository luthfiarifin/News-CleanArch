package com.laam.news_cleanarch.core.domain.repository

import com.laam.news_cleanarch.core.data.Resource
import com.laam.news_cleanarch.core.domain.model.NewsDomain
import com.laam.news_cleanarch.core.domain.model.NewsFavoriteDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by luthfiarifin on 1/10/2021.
 */
interface INewsRepository {

    fun getTopHeadline(): Flow<Resource<List<NewsDomain>>>

    fun getSearch(page: Int, q: String): Flow<Resource<List<NewsDomain>>>

    fun isNewsFavorite(url: String): Flow<Boolean>

    fun insertNewsFavorite(newsFavoriteDomain: NewsFavoriteDomain): Flow<Long>

    fun deleteNewsFavorite(url: String): Flow<Int>

    fun getAllNewsFavorite(): Flow<List<NewsFavoriteDomain>>
}