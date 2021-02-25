package com.windranger.bookfinder.ui.main

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.windranger.bookfinder.base.BaseActivity
import com.windranger.bookfinder.base.DataState
import com.windranger.bookfinder.databinding.ActivityMainBinding
import com.windranger.bookfinder.models.Book
import com.windranger.bookfinder.ui.bookmark.BookmarkActivity
import com.windranger.bookfinder.ui.detail.DetailActivity
import com.windranger.bookfinder.util.gone
import com.windranger.bookfinder.util.launchActivity
import com.windranger.bookfinder.util.visible
import com.windranger.domain.models.BookModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private val binding by viewBinding<ActivityMainBinding>()
    private val viewModel by viewModel<MainVM>()
    private val bookAdapter by lazy { BookAdapter { openDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUI()
        observeData()

        viewModel.getBooks("harry potter")
    }

    private fun initUI() {
        binding.rvBook.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = bookAdapter
        }

        binding.btnBookmark.setOnClickListener {
            launchActivity<BookmarkActivity>()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.getBooks(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeData() {
        viewModel.loading.observe(this, {
            if (it) {
                binding.rvBook.gone()
                binding.loading.visible()
            } else {
                binding.rvBook.visible()
                binding.loading.gone()
            }
        })

        viewModel.books.observe(this, {
            when (it) {
                is DataState.Success -> bookAdapter.submitList(it.data)
                is DataState.Fail -> showMessage(it.error)
            }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun openDetail(data: BookModel) {
        launchActivity<DetailActivity> {
            putExtra(EXTRA_DATA, Book.fromBookModel(data))
        }
    }
}