package com.sample.openlibrary.host

import com.sample.openlibrary.di.PerActivity
import dagger.Component

@PerActivity
@Component
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}
