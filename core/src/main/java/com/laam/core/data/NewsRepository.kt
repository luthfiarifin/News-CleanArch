package com.laam.core.data

import com.laam.core.data.source.local.LocalDataSource
import com.laam.core.data.source.remote.RemoteDataSource
import com.laam.core.data.source.remote.network.ApiResponse
import com.laam.core.data.source.remote.response.ArticleResponse
import com.laam.core.domain.model.NewsDomain
import com.laam.core.domain.repository.INewsRepository
import com.laam.core.utils.DataMapper.mapToNewsDomain
import com.laam.core.utils.DataMapper.mapToNewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by luthfiarifin on 1/10/2021.
 */
@Singleton
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : INewsRepository {

    override fun getTopHeadline(): Flow<Resource<List<NewsDomain>>> =
        object : NetworkOnlyResource<List<NewsDomain>, List<ArticleResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<ArticleResponse>>> =
                remoteDataSource.getTopHeadline()

            override suspend fun mapToResult(data: List<ArticleResponse>): List<NewsDomain> =
                data.map { it.mapToNewsDomain() }
        }.asFlow()

    override fun getSearch(page: Int, q: String): Flow<Resource<List<NewsDomain>>> =
        object : NetworkBoundResource<List<NewsDomain>, List<ArticleResponse>>() {
            override fun shouldFetch(data: List<NewsDomain>?): Boolean = true

            override fun loadFromDB(): Flow<List<NewsDomain>> =
                localDataSource.getAllNews(q).map { it.map { entity -> entity.mapToNewsDomain() } }

            override suspend fun createCall(): Flow<ApiResponse<List<ArticleResponse>>> =
                remoteDataSource.getSearch(page, q)

            override suspend fun saveCallResult(data: List<ArticleResponse>) {
                val newsEntities = data.map { it.mapToNewsEntity(q) }
                localDataSource.insertNews(newsEntities)
            }
        }.asFlow()
}