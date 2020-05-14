package com.sample.openlibrary.ui.features.bookdetails.di

import com.sample.openlibrary.di.PerFragment
import com.sample.openlibrary.ui.features.bookdetails.BookDetailsFragment
import dagger.BindsInstance
import dagger.Component

@PerFragment
@Component(
    modules = [
        BookDetailsModule::class
    ]
)
interface BookDetailsComponent {
    fun inject(fragment: BookDetailsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: BookDetailsFragment
        ): BookDetailsComponent
    }
}