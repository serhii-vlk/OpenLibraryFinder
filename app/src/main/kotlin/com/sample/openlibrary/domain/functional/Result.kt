package com.sample.openlibrary.domain.functional

import com.sample.openlibrary.data.remote.ErrorHandler

sealed class DataResult<out D> {
    data class Success<out D>(val data: D) : DataResult<D>()
    data class Error(val error: Failure) : DataResult<Nothing>()

    companion object {
        fun <D> success(data: D) = Success(data)
        fun error(failure: Failure) = Error(failure)
    }
}

sealed class Failure {
    data class ApiError(val code: Int, val message: String?) : Failure()
    object NetworkError : Failure()
    data class Unknown(val cause: Throwable) : Failure()
}

inline fun <D, E : Failure> DataResult<D>.onSuccess(block: (D) -> Unit) {
    if (this is DataResult.Success) {
        block(data)
    }
}

inline fun <D> DataResult<D>.onError(block: (Failure) -> Unit) {
    if (this is DataResult.Error) {
        block(error)
    }
}

fun <D, R> DataResult<D>.fold(onSuccess: (D) -> R, onError: (Failure) -> R): R {
    return when (this) {
        is DataResult.Success -> onSuccess(data)
        is DataResult.Error -> onError(error)
    }
}

fun <D, R> DataResult<D>.map(mapper: (D) -> R): DataResult<R> {
    return when (this) {
        is DataResult.Success -> DataResult.success(mapper(data))
        is DataResult.Error -> this
    }
}

inline fun <D, T> T.runCatchingData(
    errorHandler: ErrorHandler,
    block: T.() -> D
): DataResult<D> {
    return try {
        DataResult.success(block())
    } catch (e: Exception) {
        DataResult.error(errorHandler(e))
    }
}

fun <D> DataResult<D>.getOrElse(block: (Failure) -> D): D {
    return fold(::identity) { block(it) }
}

fun <D> DataResult<D>.getOrNull(): D? = getOrElse { null }

fun DataResult<*>.getErrorOrNull(): Failure? = fold({ null }, ::identity)

fun <A> identity(a: A): A = a
