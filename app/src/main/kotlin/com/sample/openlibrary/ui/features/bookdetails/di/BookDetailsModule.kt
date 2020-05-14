package com.sample.openlibrary.ui.features.bookdetails.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sample.openlibrary.di.PerFragment
import com.sample.openlibrary.ui.features.bookdetails.BookDetailsArgs
import com.sample.openlibrary.ui.features.bookdetails.BookDetailsFragment
import com.sample.openlibrary.ui.features.bookdetails.BookDetailsFragmentArgs
import com.sample.openlibrary.ui.features.bookdetails.BookDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
interface BookDetailsModule {
    @Binds
    @PerFragment
    fun BookDetailsFragment.bindFragment(): Fragment

    @Binds
    @PerFragment
    fun BookDetailsViewModel.Factory.bindFactory(): ViewModelProvider.Factory

    companion object {
        @Provides
        @PerFragment
        fun BookDetailsFragment.provideArgs(): BookDetailsArgs {
            val fragmentArgs = checkNotNull(arguments)
            val book = BookDetailsFragmentArgs.fromBundle(fragmentArgs).book
            return BookDetailsArgs(book)
        }
    }
}
