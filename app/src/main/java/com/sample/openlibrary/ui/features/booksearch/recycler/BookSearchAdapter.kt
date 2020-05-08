package com.sample.openlibrary.ui.features.booksearch.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.sample.openlibrary.databinding.ViewHolderBookBinding
import com.sample.openlibrary.domain.model.Book
import com.sample.openlibrary.ui.extension.inflater

class BookSearchAdapter : ListAdapter<Book, BookSearchAdapter.BookViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ViewHolderBookBinding.inflate(parent.inflater(), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class BookViewHolder(
        private val binding: ViewHolderBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book) = with(binding) {
            title.text = item.title
            author.text = item.authorsNames.joinToString(", ")
            subtitle.text = item.subtitle

            item.coverI?.let { "https://covers.openlibrary.org/w/id/$it-S.jpg" }?.also {
                cover.load(it)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }

}
