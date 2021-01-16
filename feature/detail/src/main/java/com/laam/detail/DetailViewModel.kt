package com.laam.detail

import androidx.databinding.ObservableBoolean
import com.laam.base.BaseViewModel

/**
 * Created by luthfiarifin on 1/16/2021.
 */
class DetailViewModel : BaseViewModel() {

    val isLoading = ObservableBoolean(true)
    var newsUrl: String? = null
}