package com.laam.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * Created by luthfiarifin on 1/8/2021.
 */
abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel> :
    AppCompatActivity() {

    private lateinit var mViewBinding: ViewBinding

    val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }

    val viewBinding
        get() = mViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    abstract fun getViewModelClass(): Class<ViewModel>

    abstract fun getLayoutId(): Int
}