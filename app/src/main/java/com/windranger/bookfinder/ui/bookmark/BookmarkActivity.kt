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
import com.windranger.domain.models.BookModel

class BookmarkActivity : BaseActivity() {
    private val binding by viewBinding<ActivityBookmarkBinding>()
    private val bookAdapter by lazy { BookAdapter { openDetail(it) } }

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
    }

    private fun openDetail(data: BookModel) {
        launchActivity<DetailActivity>()
    }
}