package com.sample.openlibrary.ui.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.widget.ContentLoadingProgressBar

fun ContentLoadingProgressBar.isShowing(show: Boolean) {
    if (show) {
        show()
    } else {
        hide()
    }
}

fun View.inflater() = LayoutInflater.from(context)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false) =
    inflater().inflate(layoutRes, this, attachToRoot)
