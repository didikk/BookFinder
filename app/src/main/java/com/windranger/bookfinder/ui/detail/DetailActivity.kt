package com.windranger.bookfinder.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.core.content.ContextCompat
import coil.load
import com.windranger.bookfinder.R
import com.windranger.bookfinder.base.BaseActivity
import com.windranger.bookfinder.databinding.ActivityDetailBinding
import com.windranger.bookfinder.models.Book
import com.windranger.bookfinder.util.setToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    private val binding by viewBinding<ActivityDetailBinding>()
    private val viewModel by viewModel<DetailVM>()

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setToolbar("Book Details", binding.toolbarLayout)

        val data = intent.getParcelableExtra<Book>(EXTRA_DATA)
        data?.let {
            initData(data)
            viewModel.setData(it.toBookModel())
        }

        observeData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_detail, menu)
        viewModel.checkBookmark()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_bookmark) {
            viewModel.addOrRemoveBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
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

    private fun observeData() {
        viewModel.isBookmarked.observe(this, {
            val icon =
                if (it) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24

            menu?.getItem(0)
                ?.setIcon(ContextCompat.getDrawable(this, icon))
        })
    }
}