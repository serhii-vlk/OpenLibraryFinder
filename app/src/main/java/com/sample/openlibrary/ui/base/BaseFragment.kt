package com.sample.openlibrary.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseFragment(contentLayoutId: Int = 0) : Fragment(contentLayoutId) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

}
