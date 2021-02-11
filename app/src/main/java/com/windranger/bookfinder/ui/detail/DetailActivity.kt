package com.windranger.bookfinder.ui.detail

import android.os.Bundle
import android.view.Menu
import android.viewbinding.library.activity.viewBinding
import coil.load
import com.windranger.bookfinder.R
import com.windranger.bookfinder.base.BaseActivity
import com.windranger.bookfinder.databinding.ActivityDetailBinding
import com.windranger.bookfinder.models.Book
import com.windranger.bookfinder.util.setToolbar

class DetailActivity : BaseActivity() {
    private val binding by viewBinding<ActivityDetailBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar("Book Details", binding.toolbarLayout)

        val data = intent.getParcelableExtra<Book>(EXTRA_DATA)
        data?.let {
            initData(data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    private fun initData(book: Book) {
        binding.apply {
            val publisher = "${book.publisher}, ${book.pageCount} pages"
            val author = "Author by ${book.author}"

            ivCover.load(book.cover)
            tvTitle.text = book.title
            tvAuthor.text = author
            tvPublisher.text = publisher
            tvCategory.text = book.category
            tvRating.text = book.rating.toString()
            tvPublish.text = book.publishedDate
        }
    }
}