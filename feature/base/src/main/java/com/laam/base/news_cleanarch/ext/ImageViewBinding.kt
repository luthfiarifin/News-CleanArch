package com.laam.base.news_cleanarch.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/**
 * Created by luthfiarifin on 1/10/2021.
 */
object ImageViewBinding {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageUrl(view: ImageView, imageUrl: String?) {
        imageUrl?.let { view.load(it) }
    }
}