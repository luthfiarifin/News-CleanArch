package com.laam.core.domain.repository

import com.laam.core.data.Resource
import com.laam.core.domain.model.NewsDomain
import com.laam.core.domain.model.NewsFavoriteDomain
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
}