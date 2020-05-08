package com.sample.openlibrary.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchResponse(
    @get:Json(name = "title")
    val title: String
)
