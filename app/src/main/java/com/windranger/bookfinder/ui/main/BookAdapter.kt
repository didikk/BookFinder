package com.windranger.bookfinder.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.windranger.bookfinder.databinding.ItemBookBinding
import com.windranger.domain.models.BookModel

class BookAdapter(private val listener: (data: BookModel) -> Unit) :
    ListAdapter<BookModel, BookAdapter.BookVH>(BookDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookVH {
        val binding = ItemBookBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BookVH(binding)
    }

    override fun onBindViewHolder(holder: BookVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookVH(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { listener(getItem(adapterPosition)) }
        }

        fun bind(item: BookModel) {
            binding.apply {
                ivCover.load(item.cover)
                tvTitle.text = item.title
                tvAuthor.text = item.author
                tvCategory.text = item.category
            }
        }
    }

    class BookDiff : DiffUtil.ItemCallback<BookModel>() {
        override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
            return oldItem == newItem
        }

    }

}