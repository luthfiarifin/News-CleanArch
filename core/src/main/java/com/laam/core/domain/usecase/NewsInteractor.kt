package com.laam.core.domain.usecase

import com.laam.core.data.Resource
import com.laam.core.domain.model.NewsDomain
import com.laam.core.domain.model.NewsFavoriteDomain
import com.laam.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by luthfiarifin on 1/10/2021.
 */
class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository) :
    NewsUseCase {

    override fun getTopHeadline(): Flow<Resource<List<NewsDomain>>> =
        newsRepository.getTopHeadline()

    override fun getSearch(page: Int, q: String): Flow<Resource<List<NewsDomain>>> =
        newsRepository.getSearch(page, q)

    override fun isNewsFavorite(url: String): Flow<Boolean> = newsRepository.isNewsFavorite(url)

    override fun insertNewsFavorite(newsFavoriteDomain: NewsFavoriteDomain): Flow<Long> =
        newsRepository.insertNewsFavorite(newsFavoriteDomain)
}