package com.sample.openlibrary.domain.model

data class Book(
    val title: String?,
    val subtitle: String?,
    val authorsNames: List<String>,
    val coverI: Long?
)
