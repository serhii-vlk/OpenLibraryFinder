package com.sample.openlibrary.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchResultResponse(
    @get:Json(name = "start")
    val start: Int,
    @get:Json(name = "num_found")
    val numFound: Int,
    @get:Json(name = "docs")
    val docs: List<BookSearchResponse>
)
