package com.petrulak.cleankotlin.data.source.remote

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Intercepts every request and adds `appid` into query parameters.
 */
class RequestInterceptor(private val appId: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl
            .newBuilder()
            .addQueryParameter("appid", appId)
            .build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}