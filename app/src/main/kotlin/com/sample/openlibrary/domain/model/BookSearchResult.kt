package com.sample.openlibrary.domain.model

data class BookSearchResult(
    val start: Int,
    val numFound: Int,
    val books: List<Book>
)
