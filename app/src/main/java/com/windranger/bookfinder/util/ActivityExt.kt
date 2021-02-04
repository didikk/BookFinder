package com.windranger.bookfinder.util

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.windranger.bookfinder.databinding.ToolbarLayoutBinding
import com.windranger.bookfinder.ui.detail.DetailActivity

fun AppCompatActivity.setToolbar(title: String, toolbarLayoutBinding: ToolbarLayoutBinding) {
    setSupportActionBar(toolbarLayoutBinding.toolbar)
    supportActionBar?.title = ""
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbarLayoutBinding.toolbarTitle.text = title
}

inline fun <reified T : Any> AppCompatActivity.launchActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

