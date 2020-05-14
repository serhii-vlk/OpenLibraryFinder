package com.sample.openlibrary.host

import com.sample.openlibrary.di.PerActivity
import com.sample.openlibrary.host.navigation.HostNavigator
import com.sample.openlibrary.host.navigation.HostNavigatorImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @PerActivity
    fun provideHostNavigator(activity: MainActivity): HostNavigator =
        HostNavigatorImpl(activity.navController)
}
