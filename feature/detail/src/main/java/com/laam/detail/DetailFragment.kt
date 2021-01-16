package com.laam.detail

import com.laam.base.BaseFragment
import com.laam.detail.databinding.FragmentDetailBinding

/**
 * Created by luthfiarifin on 1/16/2021.
 */
class DetailFragment: BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_detail
}