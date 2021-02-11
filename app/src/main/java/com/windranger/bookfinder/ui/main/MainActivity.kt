package com.windranger.bookfinder.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.windranger.bookfinder.databinding.ActivityMainBinding
import com.windranger.bookfinder.ui.bookmark.BookmarkActivity
import com.windranger.bookfinder.ui.detail.DetailActivity
import com.windranger.bookfinder.util.launchActivity
import com.windranger.domain.models.BookModel
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding

    private val presenter by inject<MainPresenter>()

    private val bookAdapter by lazy { BookAdapter { openDetail() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        presenter.attachView(this)
        presenter.getBooks()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun setData(data: List<BookModel>) {
        Timber.d("setData: $data")
    }

    override fun showLoading() {
        Timber.d("loading")
    }

    override fun hideLoading() {
        Timber.d("finish loading")
    }

    override fun showMessage(message: String) {
        Timber.d("message: $message")
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