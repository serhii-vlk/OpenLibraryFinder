package com.sample.openlibrary.data.repository

import com.sample.openlibrary.data.mapper.BookSearchResultMapper
import com.sample.openlibrary.data.remote.source.BooksRemoteDataSource
import com.sample.openlibrary.domain.functional.DataResult
import com.sample.openlibrary.domain.functional.map
import com.sample.openlibrary.domain.model.BookSearchResult
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val remoteDataSource: BooksRemoteDataSource,
    private val resultMapper: BookSearchResultMapper
) : BooksRepository {
    override suspend fun search(query: String): DataResult<BookSearchResult> {
        return remoteDataSource.search(query).map(resultMapper::invoke)
    }
}
