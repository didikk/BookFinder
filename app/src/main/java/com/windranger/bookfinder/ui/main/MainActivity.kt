package com.windranger.bookfinder.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.windranger.bookfinder.databinding.ActivityMainBinding
import com.windranger.bookfinder.ui.bookmark.BookmarkActivity
import com.windranger.bookfinder.ui.detail.DetailActivity
import com.windranger.bookfinder.util.launchActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bookAdapter by lazy { BookAdapter { openDetail() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        binding.rvBook.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookAdapter
        }
        bookAdapter.submitList(listOf("1", "2", "3", "4", "5", "6"))

        binding.btnBookmark.setOnClickListener {
            launchActivity<BookmarkActivity>()
        }
    }

    private fun openDetail() {
        launchActivity<DetailActivity>()
    }
}