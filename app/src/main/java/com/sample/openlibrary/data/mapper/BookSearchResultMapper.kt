package com.sample.openlibrary.data.mapper

import com.sample.openlibrary.data.remote.api.response.BookSearchResultResponse
import com.sample.openlibrary.domain.model.BookSearchResult
import javax.inject.Inject

class BookSearchResultMapper @Inject constructor(
    private val bookMapper: BookMapper
) {
    operator fun invoke(response: BookSearchResultResponse) = with(response) {
        BookSearchResult(
            start = start,
            numFound = numFound,
            books = docs.map(bookMapper::invoke)
        )
    }
}
