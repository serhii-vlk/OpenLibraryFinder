package com.sample.openlibrary.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment(contentLayoutId: Int = 0) : Fragment(contentLayoutId) {
    private var disposable: CompositeDisposable? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable = CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun Disposable.track() {
        disposable?.add(this)
    }
}
