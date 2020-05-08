package com.sample.openlibrary.ui.features.booksearch.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sample.openlibrary.di.PerFragment
import com.sample.openlibrary.ui.features.booksearch.BookSearchFragment
import com.sample.openlibrary.ui.features.booksearch.BookSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface BookSearchModule {
    @Binds
    @PerFragment
    fun BookSearchFragment.bindFragment(): Fragment

    @Binds
    @PerFragment
    fun BookSearchViewModel.Factory.bindFactory(): ViewModelProvider.Factory

    companion object {
        @Provides
        @PerFragment
        fun Fragment.provideResources() = resources
    }
}
