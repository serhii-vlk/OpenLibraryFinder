package com.sample.openlibrary.test

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

class MockResponseException(
    code: Int = 500,
    message: String = "response_error",
    mimeType: String = "text/plain"
) : HttpException(Response.error<Any>(code, message.toResponseBody(mimeType.toMediaType())))
