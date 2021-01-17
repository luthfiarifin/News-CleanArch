package com.laam.base.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by luthfiarifin on 1/17/2021.
 */
object SnackBarUtil {

    fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) =
        Snackbar.make(this, message, duration).show()
}