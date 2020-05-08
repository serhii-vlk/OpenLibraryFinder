package com.sample.openlibrary.data.remote.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookSearchResponse(
    @get:Json(name = "title")
    val title: String? = null,
    @get:Json(name = "subtitle")
    val subtitle: String? = null,
    @get:Json(name = "author_name")
    val authorName: List<String>? = null,
    @get:Json(name = "cover_i")
    val coverI: Long? = null
)
