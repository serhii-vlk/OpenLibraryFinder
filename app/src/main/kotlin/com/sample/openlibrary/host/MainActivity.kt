package com.sample.openlibrary.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.sample.openlibrary.R
import com.sample.openlibrary.databinding.ActivityMainBinding
import com.sample.openlibrary.di.AppComponentProvider
import javax.inject.Provider

class MainActivity : AppCompatActivity(), Provider<HostProvider> {
    val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        findNavController(R.id.nav_host_fragment).also {
            val config = AppBarConfiguration(setOf(R.id.nav_bookSearch))
            binding.toolbar.setupWithNavController(it, config)
        }
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)

    private val provider by lazy(LazyThreadSafetyMode.NONE) {
        val appComponent = (applicationContext as AppComponentProvider).getAppComponent()
        DaggerMainActivityComponent.factory()
            .create(
                activity = this,
                dataProvider = appComponent
            )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupToolbarWithNavController()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun get() = provider

    private fun setupToolbarWithNavController() {
        val config = AppBarConfiguration(setOf(R.id.nav_bookSearch))
        binding.toolbar.setupWithNavController(navController, config)
    }
}
