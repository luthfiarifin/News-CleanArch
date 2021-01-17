package com.laam.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by luthfiarifin on 1/8/2021.
 */
abstract class BaseFragment<ViewBinding : ViewDataBinding> : Fragment() {

    private lateinit var mViewBinding: ViewBinding

    val viewBinding
        get() = mViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        return mViewBinding.root
    }

    abstract fun getLayoutId(): Int

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}