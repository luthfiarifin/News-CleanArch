package com.laam.core.domain.usecase

import com.laam.core.data.Resource
import com.laam.core.domain.model.NewsDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by luthfiarifin on 1/10/2021.
 */
interface NewsUseCase {

    fun getTopHeadline(): Flow<Resource<List<NewsDomain>>>

    fun getSearch(page: Int, q: String): Flow<Resource<List<NewsDomain>>>
}