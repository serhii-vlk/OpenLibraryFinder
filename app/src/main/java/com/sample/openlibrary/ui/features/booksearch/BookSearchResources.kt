package com.sample.openlibrary.ui.features.booksearch

import android.content.res.Resources
import com.sample.openlibrary.R
import javax.inject.Inject

class BookSearchResources @Inject constructor(private val resources: Resources) {
    fun queryResult(query: String) =
        resources.getString(R.string.book_search_query_result_formatted, query)
}
