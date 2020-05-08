package com.sample.openlibrary.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseViewModel<S : BaseViewModel.ViewUiState>(
    private val initState: S
) : ViewModel() {
    protected var currentState = initState
    private val mutableState = BehaviorSubject.create<S>().apply {
        onNext(initState)
    }
    val state: Observable<S> = mutableState
        .doAfterNext { currentState = it }

    protected fun updateState(reduce: S.() -> S) {
        mutableState.onNext(reduce(currentState))
    }

    interface ViewUiState
}
