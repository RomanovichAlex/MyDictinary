package by.romanovich.mydictinary.data.api

import by.romanovich.mydictinary.data.utils.*
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class BaseInterceptor private constructor() : Interceptor {
    private var responseCode: Int = 0

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code()
        return response
    }

    fun getResponseCode(): ServerResponseStatusCode {
        var statusCode = ServerResponseStatusCode.UNDEFINED_ERROR
        when (responseCode / AVERAGE_STATUS_CODE) {
            INFO -> statusCode = ServerResponseStatusCode.INFO
            SUCCESS -> statusCode = ServerResponseStatusCode.SUCCESS
            REDIRECTION -> statusCode = ServerResponseStatusCode.REDIRECTION
            CLIENT_ERROR -> statusCode = ServerResponseStatusCode.CLIENT_ERROR
            SERVER_ERROR -> statusCode = ServerResponseStatusCode.SERVER_ERROR
        }
        return statusCode
    }

    enum class ServerResponseStatusCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }

    companion object {
        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }
}