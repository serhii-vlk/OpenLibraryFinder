package com.sample.openlibrary.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.openlibrary.R
import com.sample.openlibrary.di.AppComponent
import com.sample.openlibrary.di.AppComponentProvider
import javax.inject.Provider

class MainActivity : AppCompatActivity(), Provider<AppComponent> {
    private val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        (applicationContext as AppComponentProvider).getAppComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun get() = appComponent
}
