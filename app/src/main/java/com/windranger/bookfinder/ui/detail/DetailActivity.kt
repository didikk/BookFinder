package com.windranger.bookfinder.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.windranger.bookfinder.R
import com.windranger.bookfinder.base.BaseActivity
import com.windranger.bookfinder.databinding.ActivityDetailBinding
import com.windranger.bookfinder.util.setToolbar

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar("Book Details", binding.toolbarLayout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }
}