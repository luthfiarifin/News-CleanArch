package com.laam.core.utils

import com.laam.core.data.source.local.entity.NewsEntity
import com.laam.core.data.source.remote.response.ArticleResponse
import com.laam.core.domain.model.NewsDomain
import com.laam.core.presentation.model.News

/**
 * Created by luthfiarifin on 1/10/2021.
 */
object DataMapper {

    fun NewsEntity.mapToNewsDomain() = NewsDomain(
        url, author, content, description, publishedAt, source, title, urlToImage
    )

    fun ArticleResponse.mapToNewsDomain() = NewsDomain(
        url, author, content, description, publishedAt, source.name, title, urlToImage
    )

    fun ArticleResponse.mapToNewsEntity() = NewsEntity(
        url, author, content, description, publishedAt, source.name, title, urlToImage
    )

    fun NewsDomain.mapToNews() = News(
        url, content, publishedAt, source, title, urlToImage
    )
}