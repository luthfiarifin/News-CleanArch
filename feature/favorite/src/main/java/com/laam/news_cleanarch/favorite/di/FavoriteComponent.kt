package com.laam.news_cleanarch.favorite.di

import android.content.Context
import com.laam.news_cleanarch.di.FavoriteModuleDependencies
import com.laam.news_cleanarch.favorite.FavoriteListFragment
import dagger.BindsInstance
import dagger.Component

/**
 * Created by luthfiarifin on 1/18/2021.
 */
@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteListFragment)

    @Component.Builder
    interface Builder {

        fun context(@BindsInstance context: Context): Builder

        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder

        fun build(): FavoriteComponent
    }
}