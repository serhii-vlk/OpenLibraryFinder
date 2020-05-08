package com.sample.openlibrary.domain.functional

import com.sample.openlibrary.data.remote.ErrorHandler

sealed class DataResult<out D : Any> {
    data class Success<out D : Any>(val data: D) : DataResult<D>()
    data class Error(val error: Failure) : DataResult<Nothing>()

    companion object {
        fun <D : Any> success(data: D) = Success(data)
        fun error(failure: Failure) = Error(failure)
    }
}

sealed class Failure {
    data class ApiError(val message: String, val code: Int) : Failure()
    object NetworkError : Failure()
    data class Error(val throwable: Throwable) : Failure()
}

inline fun <D : Any, E : Failure> DataResult<D>.onSuccess(block: (D) -> Unit) {
    if (this is DataResult.Success) {
        block(data)
    }
}

inline fun <D : Any> DataResult<D>.onError(block: (Failure) -> Unit) {
    if (this is DataResult.Error) {
        block(error)
    }
}

fun <D : Any, R : Any> DataResult<D>.map(mapper: (D) -> R): DataResult<R> {
    return when (this) {
        is DataResult.Success -> DataResult.success(mapper(data))
        is DataResult.Error -> this
    }
}

inline fun <D : Any, T : Any> T.runCatchingData(
    errorHandler: ErrorHandler,
    block: T.() -> D
): DataResult<D> {
    return try {
        DataResult.success(block())
    } catch (e: Exception) {
        DataResult.error(errorHandler(e))
    }
}
