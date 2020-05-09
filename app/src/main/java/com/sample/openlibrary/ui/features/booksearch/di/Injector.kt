package com.sample.openlibrary.ui.features.booksearch.di

import com.sample.openlibrary.host.HostProvider
import com.sample.openlibrary.ui.features.booksearch.BookSearchFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun BookSearchFragment.inject(hostProvider: HostProvider) {
    DaggerBookSearchComponent.factory()
        .create(
            fragment = this,
            hostProvider = hostProvider
        ).inject(this)
}
