package com.sample.openlibrary.ui.features.bookdetails.di

import com.sample.openlibrary.ui.features.bookdetails.BookDetailsFragment

fun BookDetailsFragment.inject() {
    DaggerBookDetailsComponent.factory()
        .create(this)
        .inject(this)
}
