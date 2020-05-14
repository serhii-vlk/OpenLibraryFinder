package com.sample.openlibrary.ui.features.bookdetails

import com.sample.openlibrary.domain.model.Book
import com.sample.openlibrary.domain.model.coverMediumUrl
import com.sample.openlibrary.ui.base.BaseViewModel
import com.sample.openlibrary.ui.base.SimpleViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
private fun Book.toUiState() = BookDetailsViewModel.UiState(
    title = title ?: "",
    subtitle = subtitle ?: "",
    authors = authorsNames.joinToString(", "),
    coverUrl = coverMediumUrl
)

@ExperimentalCoroutinesApi
class BookDetailsViewModel @Inject constructor(
    args: BookDetailsArgs
) : BaseViewModel<BookDetailsViewModel.UiState>(args.book.toUiState()) {

    data class UiState(
        val title: String,
        val subtitle: String,
        val authors: String,
        val coverUrl: String?
    ) : ViewUiState

    class Factory @Inject constructor(viewModel: BookDetailsViewModel) :
        SimpleViewModelFactory<BookDetailsViewModel>(viewModel)
}

class BookDetailsArgs(
    val book: Book
)
