package com.sample.openlibrary.data.remote

import com.sample.openlibrary.domain.functional.Failure
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface ErrorHandler {
    operator fun invoke(throwable: Throwable): Failure
}

class RemoteErrorHandler @Inject constructor() : ErrorHandler {
    override fun invoke(throwable: Throwable): Failure {
        return when (throwable) {
            is IOException -> Failure.NetworkError
            is HttpException -> {
                Failure.ApiError(
                    message = throwable.response()?.errorBody()?.string(),
                    code = throwable.code()
                )
            }
            else -> Failure.Unknown(throwable)
        }
    }
}
