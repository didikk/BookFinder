package com.windranger.bookfinder.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.windranger.bookfinder.databinding.ItemBookBinding

class BookAdapter(private val listener: () -> Unit) :
    ListAdapter<String, BookAdapter.BookVH>(BookDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        val binding = ItemBookBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BookVH(binding.root)
    }

    override fun onBindViewHolder(holder: BookVH, position: Int) {

    }

    inner class BookVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { listener() }
        }
    }

    class BookDiff : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

}