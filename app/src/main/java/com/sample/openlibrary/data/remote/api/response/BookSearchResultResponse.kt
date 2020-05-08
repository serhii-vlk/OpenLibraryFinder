package com.sample.openlibrary.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchResultResponse(
    @get:Json(name = "title")
    val title: String,
    @get:Json(name = "cover_i")
    val coverId: Long
)
