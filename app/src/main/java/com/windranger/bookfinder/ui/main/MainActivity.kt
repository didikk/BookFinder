package com.windranger.bookfinder.ui.main

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.windranger.bookfinder.databinding.ActivityMainBinding
import com.windranger.bookfinder.ui.bookmark.BookmarkActivity
import com.windranger.bookfinder.ui.detail.DetailActivity
import com.windranger.bookfinder.util.launchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityMainBinding>()
    private val viewModel by viewModel<MainVM>()
    private val bookAdapter by lazy { BookAdapter { openDetail() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()

        viewModel.getBooks()
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