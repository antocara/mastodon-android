package org.joinmastodon.android.data.network

import okhttp3.OkHttpClient
import org.joinmastodon.android.data.network.interceptors.NetworkInterceptor

class ClientOkHttp {
    companion object {
        fun build(interceptors: List<NetworkInterceptor>? = emptyList()): OkHttpClient {
            return OkHttpClient.Builder()
                .apply {
                    interceptors?.forEach {
                        addInterceptor(it.interceptor)
                    }
                }.build()
        }
    }
}