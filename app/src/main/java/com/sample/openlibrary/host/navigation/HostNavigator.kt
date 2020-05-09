package com.sample.openlibrary.host.navigation

import androidx.navigation.NavController
import com.sample.openlibrary.domain.model.Book
import com.sample.openlibrary.ui.features.booksearch.BookSearchFragmentDirections

interface HostNavigator {
    fun actionBookDetails(book: Book)
}

class HostNavigatorImpl(
    private val navController: NavController
) : HostNavigator {
    override fun actionBookDetails(book: Book) {
        val direction =
            BookSearchFragmentDirections.navActionBookSearchToBookDetails(book)
        navController.navigate(direction)
    }
}
