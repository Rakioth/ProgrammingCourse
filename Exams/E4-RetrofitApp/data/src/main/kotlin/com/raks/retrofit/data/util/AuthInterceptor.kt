package com.raks.retrofit.data.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val authToken: String
) : Interceptor {

    companion object {
        private const val HEADER = "X-Api-Key"
    }

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(HEADER, authToken)
                .build()
        )

}