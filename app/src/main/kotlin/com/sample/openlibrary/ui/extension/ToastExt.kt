package com.sample.openlibrary.ui.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.toast(text: CharSequence): Toast =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).also { it.show() }

fun Context.toast(@StringRes resId: Int) = toast(resources.getText(resId))

fun Fragment.toast(text: CharSequence) = requireContext().toast(text)
fun Fragment.toast(@StringRes resId: Int) = requireContext().toast(resId)
