package com.sample.openlibrary.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
abstract class SimpleViewModelFactory<VM : BaseViewModel<*>>(private val viewModel: VM) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}