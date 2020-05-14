package com.sample.openlibrary.test

import com.sample.openlibrary.data.remote.api.response.BookSearchResponse
import com.sample.openlibrary.data.remote.api.response.BookSearchResultResponse

val mockBooksResponses = listOf(
    BookSearchResponse("Foo", "foo", listOf("foo author"), 1),
    BookSearchResponse("Bar", "bar", listOf("bar author"), 2)
)

val mockSearchResult = BookSearchResultResponse(
    start = 6,
    numFound = 10,
    docs = mockBooksResponses
)
