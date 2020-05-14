package com.sample.openlibrary.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
abstract class BaseViewModel<S : BaseViewModel.ViewUiState>(initState: S) : ViewModel() {

    private val mutableState = MutableStateFlow(initState)
    val state: StateFlow<S> get() = mutableState

    protected fun reduceState(reduce: S.() -> S) {
        mutableState.value = reduce(state.value)
    }

    interface ViewUiState
}
