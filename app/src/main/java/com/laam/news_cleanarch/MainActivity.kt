package com.laam.news_cleanarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.laam.news_cleanarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by luthfiarifin on 1/9/2021.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(viewBinding.appToolbar.toolbar)
    }
}