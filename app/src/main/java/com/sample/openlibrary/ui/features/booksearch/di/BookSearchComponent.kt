package com.sample.openlibrary.ui.features.booksearch.di

import com.sample.openlibrary.di.PerFragment
import com.sample.openlibrary.host.HostProvider
import com.sample.openlibrary.ui.features.booksearch.BookSearchFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@PerFragment
@Component(
    modules = [BookSearchModule::class],
    dependencies = [HostProvider::class]
)
interface BookSearchComponent {
    fun inject(fragment: BookSearchFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: BookSearchFragment,
            hostProvider: HostProvider
        ): BookSearchComponent
    }
}
