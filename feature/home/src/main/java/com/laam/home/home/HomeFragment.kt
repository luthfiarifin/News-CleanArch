package com.laam.home.home

import android.os.Bundle
import android.view.View
import com.laam.base.BaseFragment
import com.laam.home.R
import com.laam.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/8/2021.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}