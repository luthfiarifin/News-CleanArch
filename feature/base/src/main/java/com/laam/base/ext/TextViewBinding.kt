package com.laam.base.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.laam.base.ext.TimeUtils.toTimeAgo

/**
 * Created by luthfiarifin on 1/10/2021.
 */
object TextViewBinding {

    @JvmStatic
    @BindingAdapter("timeAgo")
    fun bindTimeAgo(view: TextView, timeAgo: String?) {
        timeAgo?.let { view.text = it.toTimeAgo() }
    }
}