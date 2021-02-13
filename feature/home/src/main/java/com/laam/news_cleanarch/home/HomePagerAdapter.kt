package com.laam.news_cleanarch.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.laam.news_cleanarch.home.list.NewsListFragment

/**
 * Created by luthfiarifin on 1/10/2021.
 */
class HomePagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val categoryList: List<String>
) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = categoryList.size

    override fun createFragment(position: Int): Fragment =
        NewsListFragment.newInstance(categoryList[position])
}