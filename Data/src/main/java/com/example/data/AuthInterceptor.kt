package com.example.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .also {
                it.header("x-api-key", "live_Am71C8UhuEYHSBgCBDguKrcpcRdSVgcBE6YkLvF5xA4YD2PlaPzgpvHZ8rMRsTar")
            }
            .build()

        return chain.proceed(request)
    }
}