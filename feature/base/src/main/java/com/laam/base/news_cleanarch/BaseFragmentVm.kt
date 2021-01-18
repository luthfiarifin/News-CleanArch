package com.laam.base.news_cleanarch

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * Created by luthfiarifin on 1/8/2021.
 */
abstract class BaseFragmentVm<ViewBinding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel> : BaseFragment<ViewBinding>() {

    private lateinit var mViewBinding: ViewBinding

    val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }

    abstract fun getViewModelClass(): Class<ViewModel>
}