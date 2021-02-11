package com.windranger.bookfinder.ui.bookmark

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.windranger.bookfinder.base.BaseActivity
import com.windranger.bookfinder.databinding.ActivityBookmarkBinding
import com.windranger.bookfinder.ui.detail.DetailActivity
import com.windranger.bookfinder.ui.main.BookAdapter
import com.windranger.bookfinder.util.launchActivity
import com.windranger.bookfinder.util.setToolbar

class BookmarkActivity : BaseActivity() {
    private val binding by viewBinding<ActivityBookmarkBinding>()
    private val bookAdapter by lazy { BookAdapter { openDetail() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar("Bookmarks", binding.toolbarLayout)
        initUI()
    }

    private fun initUI() {
        binding.rvBook.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookAdapter
        }
        bookAdapter.submitList(listOf("1", "2", "3", "4", "5", "6"))
    }

    private fun openDetail() {
        launchActivity<DetailActivity>()
    }
}