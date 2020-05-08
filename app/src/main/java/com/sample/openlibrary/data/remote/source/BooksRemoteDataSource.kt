package com.sample.openlibrary.data.remote.source

import com.sample.openlibrary.data.remote.ErrorHandler
import com.sample.openlibrary.data.remote.api.OpenLibraryApi
import com.sample.openlibrary.data.remote.api.response.BookSearchResultResponse
import com.sample.openlibrary.domain.functional.DataResult
import com.sample.openlibrary.domain.functional.runCatchingData
import javax.inject.Inject

interface BooksRemoteDataSource {
    suspend fun search(query: String): DataResult<List<BookSearchResultResponse>>
}

class BooksRemoteDataSourceImpl @Inject constructor(
    private val api: OpenLibraryApi,
    private val errorHandler: ErrorHandler
) : BooksRemoteDataSource {
    override suspend fun search(query: String): DataResult<List<BookSearchResultResponse>> {
        return api.runCatchingData(errorHandler) { search(query) }
    }

}
