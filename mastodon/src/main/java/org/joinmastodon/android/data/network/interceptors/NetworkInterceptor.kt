package org.joinmastodon.android.data.network.interceptors

import okhttp3.Interceptor

interface NetworkInterceptor {
    val interceptor: Interceptor
}