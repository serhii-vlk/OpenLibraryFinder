package com.sample.openlibrary.data.remote.source

import com.google.common.truth.Truth.assertThat
import com.sample.openlibrary.data.remote.ErrorHandler
import com.sample.openlibrary.data.remote.RemoteErrorHandler
import com.sample.openlibrary.data.remote.api.OpenLibraryApi
import com.sample.openlibrary.data.remote.api.response.BookSearchResponse
import com.sample.openlibrary.data.remote.api.response.BookSearchResultResponse
import com.sample.openlibrary.domain.functional.DataResult
import com.sample.openlibrary.domain.functional.Failure
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class BooksRemoteDataSourceTest {
    private val openLibraryApi: OpenLibraryApi = mockk()
    private val errorHandler: ErrorHandler = spyk(RemoteErrorHandler())
    private lateinit var sut: BooksRemoteDataSource

    @BeforeEach
    fun setUp() {
        sut = BooksRemoteDataSourceImpl(openLibraryApi, errorHandler)
    }

    @Test
    internal fun `when api returns book list should returned some list wrapped Success`() =
        runBlockingTest {
            val books = listOf(
                BookSearchResponse("Foo", "foo", listOf("foo author"), 1),
                BookSearchResponse("Bar", "bar", listOf("bar author"), 2)
            )
            coEvery { openLibraryApi.search(any()) } returns BookSearchResultResponse(
                start = 0,
                numFound = books.size,
                docs = books
            )

            val result = sut.search("")
            val list = (result as DataResult.Success).data.docs
            assertThat(list).containsExactlyElementsIn(books)
        }

    @Test
    fun `when api returns error should returned some error wrapped Failure_Error`() =
        runBlockingTest {
            val error = Exception("api error")
            coEvery { openLibraryApi.search(any()) } throws error

            val result = sut.search("")

            val throwable = ((result as DataResult.Error).error as Failure.Error).throwable
            assertThat(throwable).isEqualTo(error)
        }

    @Test
    internal fun `when api returned error should call errorHandler invoke`() = runBlockingTest {
        val error = Exception("error")
        coEvery { openLibraryApi.search(any()) } throws error

        sut.search("")

        verify { errorHandler.invoke(error) }
        confirmVerified(errorHandler)
    }
}
