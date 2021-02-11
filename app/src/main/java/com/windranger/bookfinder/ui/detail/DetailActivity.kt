package com.windranger.bookfinder.ui.detail

import android.os.Bundle
import android.view.Menu
import android.viewbinding.library.activity.viewBinding
import com.windranger.bookfinder.R
import com.windranger.bookfinder.base.BaseActivity
import com.windranger.bookfinder.databinding.ActivityDetailBinding
import com.windranger.bookfinder.util.setToolbar

class DetailActivity : BaseActivity() {
    private val binding by viewBinding<ActivityDetailBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar("Book Details", binding.toolbarLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }
}