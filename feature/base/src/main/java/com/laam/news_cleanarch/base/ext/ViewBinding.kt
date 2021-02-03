package com.laam.news_cleanarch.base.ext

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * Created by luthfiarifin on 1/10/2021.
 */
object ViewBinding {

    @JvmStatic
    @BindingAdapter("isVisible")
    fun bindIsVisible(view: View, isVisible: Boolean?) {
        isVisible?.let { view.visibility = if (isVisible) View.VISIBLE else View.GONE }
    }
}