package com.sample.openlibrary.data.repository

import com.sample.openlibrary.domain.functional.DataResult
import com.sample.openlibrary.domain.model.BookSearchResult

interface BooksRepository {
    suspend fun search(query: String): DataResult<BookSearchResult>
}