package com.sample.openlibrary.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String?,
    val subtitle: String?,
    val authorsNames: List<String>,
    val coverI: Long?
) : Parcelable

val Book.coverSmallUrl
    get() = coverI?.let { "https://covers.openlibrary.org/w/id/$it-S.jpg" }

val Book.coverMediumUrl
    get() = coverI?.let { "https://covers.openlibrary.org/w/id/$it-M.jpg" }

