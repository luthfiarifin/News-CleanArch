package com.laam.home.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.laam.home.home.list.NewsListFragment

/**
 * Created by luthfiarifin on 1/10/2021.
 */
class HomePagerAdapter(fragmentActivity: FragmentActivity, private val categoryList: List<String>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = categoryList.size

    override fun createFragment(position: Int): Fragment =
        NewsListFragment.newInstance(categoryList[position])
}