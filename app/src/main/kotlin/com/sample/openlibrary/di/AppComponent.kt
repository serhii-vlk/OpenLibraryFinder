package com.sample.openlibrary.di

import com.sample.openlibrary.App
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    modules = [
        DataModule::class
    ]
)
interface AppComponent : DataProvider {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }

    companion object Initializer {
        fun init(app: App): AppComponent {
            return DaggerAppComponent.factory()
                .create(app)
        }
    }
}

interface AppComponentProvider {
    fun getAppComponent(): AppComponent
}
