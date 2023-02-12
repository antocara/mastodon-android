package org.joinmastodon.android.data.network.interceptors

import okhttp3.Interceptor

class HeadersInterceptor(private val headers: List<Header>) : NetworkInterceptor {

    override val interceptor = Interceptor { chain ->

        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .apply {
                        headers.forEach {
                            addHeader(it.key, it.value)
                        }
                    }
                    .build()
            )
        }
    }
}