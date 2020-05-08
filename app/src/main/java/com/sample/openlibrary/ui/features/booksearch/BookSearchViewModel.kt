package com.sample.openlibrary.ui.features.booksearch

import androidx.lifecycle.viewModelScope
import com.sample.openlibrary.data.repository.BooksRepository
import com.sample.openlibrary.domain.functional.DataResult
import com.sample.openlibrary.domain.functional.Event
import com.sample.openlibrary.domain.functional.Failure
import com.sample.openlibrary.domain.functional.toEvent
import com.sample.openlibrary.domain.model.Book
import com.sample.openlibrary.ui.base.BaseViewModel
import com.sample.openlibrary.ui.base.SimpleViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class BookSearchViewModel @Inject constructor(
    private val resources: BookSearchResources,
    private val booksRepository: BooksRepository
) : BaseViewModel<BookSearchViewModel.UiState>(UiState()) {
    private var searchJob: Job? = null
    private var lastQuery: String? = null

    fun searchQuery(query: String) {
        Timber.d(query)
        if (lastQuery == query) {
            return
        }
        lastQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            updateState { copy(loading = true, isEmpty = false) }
            when (val result = booksRepository.search(query)) {
                is DataResult.Success -> updateState {
                    val books = result.data.books
                    copy(
                        loading = false,
                        query = resources.queryResult(query),
                        books = books,
                        isEmpty = books.isEmpty()
                    )
                }
                is DataResult.Error -> {
                    val error = result.error
                    Timber.e(error.toString())
                    val message = when (error) {
                        is Failure.ApiError -> error.message
                        Failure.NetworkError -> "Network Error"
                        is Failure.Error -> error.throwable.message
                    }
                    updateState { copy(loading = false, toast = message?.toEvent()) }
                }
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val query: String = "",
        val books: List<Book> = emptyList(),
        val isEmpty: Boolean = false,
        val toast: Event<String>? = null
    ) : ViewUiState

    class Factory @Inject constructor(viewModel: BookSearchViewModel) :
        SimpleViewModelFactory<BookSearchViewModel>(viewModel)
}
