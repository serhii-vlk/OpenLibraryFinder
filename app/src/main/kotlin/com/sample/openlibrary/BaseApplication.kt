package com.sample.openlibrary

import android.app.Application
import com.sample.openlibrary.di.AppComponent
import com.sample.openlibrary.di.AppComponentProvider
import timber.log.Timber

class BaseApplication : Application(), App, AppComponentProvider {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponent.init(this)

        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun getAppComponent() = appComponent
}
