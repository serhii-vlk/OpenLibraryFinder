package com.sample.openlibrary.ui.features.booksearch.di

import com.sample.openlibrary.di.DataProvider
import com.sample.openlibrary.ui.features.booksearch.BookSearchFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun BookSearchFragment.inject(dataProvider: DataProvider) {
    DaggerBookSearchComponent.factory()
        .create(
            fragment = this,
            dataProvider = dataProvider
        ).inject(this)
}
