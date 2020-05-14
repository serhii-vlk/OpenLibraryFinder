package com.sample.openlibrary.data.remote.api

import com.sample.openlibrary.data.remote.api.response.BookSearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun search(@Query("q") query: String): BookSearchResultResponse
}
