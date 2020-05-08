package com.sample.openlibrary.data.mapper

import com.sample.openlibrary.data.remote.api.response.BookSearchResponse
import com.sample.openlibrary.domain.model.Book
import javax.inject.Inject

class BookMapper @Inject constructor() {
    operator fun invoke(response: BookSearchResponse) = with(response) {
        Book(
            title = title,
            subtitle = subtitle,
            authorsNames = authorName.orEmpty(),
            coverI = coverI
        )
    }
}
