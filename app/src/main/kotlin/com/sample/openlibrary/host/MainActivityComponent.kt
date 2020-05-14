package com.sample.openlibrary.host

import com.sample.openlibrary.di.DataProvider
import com.sample.openlibrary.di.PerActivity
import com.sample.openlibrary.host.navigation.HostNavigator
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(
    modules = [MainActivityModule::class],
    dependencies = [DataProvider::class]
)
interface MainActivityComponent : HostProvider {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: MainActivity,
            dataProvider: DataProvider
        ): MainActivityComponent
    }
}

interface HostProvider : DataProvider {
    fun provideHostNavigator(): HostNavigator
}
