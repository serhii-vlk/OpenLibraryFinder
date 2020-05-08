package com.sample.openlibrary.ui.features.booksearch

import com.sample.openlibrary.data.remote.source.BooksRemoteDataSource
import com.sample.openlibrary.ui.base.BaseViewModel
import com.sample.openlibrary.ui.base.SimpleViewModelFactory
import javax.inject.Inject

class BookSearchViewModel @Inject constructor(
    private val booksRemoteDataSource: BooksRemoteDataSource
) : BaseViewModel() {

    class Factory @Inject constructor(viewModel: BookSearchViewModel) :
        SimpleViewModelFactory<BookSearchViewModel>(viewModel)
}
