package com.sample.openlibrary.data.remote.source

import com.google.common.truth.Truth.assertThat
import com.sample.openlibrary.data.remote.ErrorHandler
import com.sample.openlibrary.data.remote.RemoteErrorHandler
import com.sample.openlibrary.data.remote.api.OpenLibraryApi
import com.sample.openlibrary.domain.functional.DataResult
import com.sample.openlibrary.domain.functional.Failure
import com.sample.openlibrary.domain.functional.getErrorOrNull
import com.sample.openlibrary.test.MockResponseException
import com.sample.openlibrary.test.TestCoroutinesRule
import com.sample.openlibrary.test.mockSearchResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.io.IOException

@ExperimentalCoroutinesApi
class BooksRemoteDataSourceTest {
    @RegisterExtension
    @JvmField
    val testCoroutinesRule = TestCoroutinesRule()

    private val openLibraryApi: OpenLibraryApi = mockk()
    private val errorHandler: ErrorHandler = RemoteErrorHandler()

    private lateinit var sut: BooksRemoteDataSource

    @BeforeEach
    fun setUp() {
        sut = BooksRemoteDataSourceImpl(openLibraryApi, errorHandler)
    }

    @Test
    internal fun `search book search query passed to api service`() =
        testCoroutinesRule.runBlocking {
            sut.search(SEARCH_QUERY)

            val querySlot = slot<String>()
            coVerify {
                openLibraryApi.search(capture(querySlot))
            }
            assertThat(querySlot.captured).isEqualTo(SEARCH_QUERY)
        }

    @Test
    internal fun `search book success returned data`() =
        runBlockingTest {
            success()

            val result = (sut.search("") as DataResult.Success).data
            assertThat(result.start).isEqualTo(mockSearchResult.start)
            assertThat(result.numFound).isEqualTo(mockSearchResult.numFound)
            assertThat(result.docs).containsExactlyElementsIn(mockSearchResult.docs)
        }

    @Test
    internal fun `search book unknown error returned failure`() = testCoroutinesRule.runBlocking {
        unknownError()

        val result = sut.search("")

        val throwable = ((result as DataResult.Error).error as Failure.Unknown).cause
        assertThat(throwable).isEqualTo(unknownErrorMock)
    }

    @Test
    internal fun `search book network error returned failure`() = testCoroutinesRule.runBlocking {
        networkError()

        val result = sut.search("")
        val error = (result as DataResult.Error).error
        assertThat(error).isInstanceOf(Failure.NetworkError::class.java)
    }

    @Test
    internal fun `search book api error returned failure`() = testCoroutinesRule.runBlocking {
        apiError()

        val error = sut.search("").getErrorOrNull() as Failure.ApiError

        assertThat(error.code).isEqualTo(API_ERROR_CODE)
        assertThat(error.message).isEqualTo(API_ERROR_MESSAGE)
    }

    private fun success() {
        coEvery { openLibraryApi.search(any()) } returns mockSearchResult
    }

    private fun networkError() {
        coEvery { openLibraryApi.search(any()) } throws IOException()
    }

    private fun unknownError() {
        coEvery { openLibraryApi.search(any()) } throws unknownErrorMock
    }

    private fun apiError() {
        coEvery { openLibraryApi.search(any()) } throws apiErrorMock
    }

    companion object {
        private const val SEARCH_QUERY = "search query"
        private const val UNKNOWN_ERROR_MESSAGE = "unknown error"
        private val unknownErrorMock = object : Exception(UNKNOWN_ERROR_MESSAGE) {}
        private const val API_ERROR_CODE = 500
        private const val API_ERROR_MESSAGE = "api error"
        private val apiErrorMock = MockResponseException(API_ERROR_CODE, API_ERROR_MESSAGE)
    }
}
