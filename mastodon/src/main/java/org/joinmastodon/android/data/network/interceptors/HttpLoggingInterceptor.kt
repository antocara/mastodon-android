package org.joinmastodon.android.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class HttpLoggingInterceptor : NetworkInterceptor {

    override val interceptor: Interceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}