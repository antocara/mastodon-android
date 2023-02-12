package org.joinmastodon.android.di

import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.network.NetworkClient
import org.joinmastodon.android.data.network.interceptors.HttpLoggingInterceptor
import org.joinmastodon.android.data.network.interceptors.NetworkInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MASTODON_API = "MASTODON_API"
const val MASTODON_API_BASE_URL = "MASTODON_API_BASE_URL"
const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"
const val NETWORK_CLIENT = "NETWORK_CLIENT"

val mastodonApiModule = module {
    single(named(MASTODON_API_BASE_URL)) { "https://api.joinmastodon.org" }

    factory<MastodonApi>(named(MASTODON_API)) {
        get<NetworkClient>(named(NETWORK_CLIENT))
            .build().create(MastodonApi::class.java)
    }

    factory<NetworkClient>(named(NETWORK_CLIENT)) {
        NetworkClient(
            interceptors = listOf(
                get<NetworkInterceptor>(named(LOGGING_INTERCEPTOR)),
            ),
            baseUrl = get(named(MASTODON_API_BASE_URL))
        )
    }

    single<NetworkInterceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor()
    }

}