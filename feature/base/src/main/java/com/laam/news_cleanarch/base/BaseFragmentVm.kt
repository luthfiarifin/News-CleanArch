package com.laam.news_cleanarch.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * Created by luthfiarifin on 1/8/2021.
 */
abstract class BaseFragmentVm<ViewBinding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel> : BaseFragment<ViewBinding>() {

    val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }

    abstract fun getViewModelClass(): Class<ViewModel>
}