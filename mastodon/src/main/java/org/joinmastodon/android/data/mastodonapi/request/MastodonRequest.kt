package org.joinmastodon.android.data.mastodonapi.request

import org.joinmastodon.android.data.mastodonapi.MastodonApi

abstract class MastodonRequest<I, O>(val apiClient: MastodonApi) {

    companion object {
        const val HTTPS = "https://"
    }

    abstract suspend fun execute(domainUrl: String, body: I): O?

    abstract fun buildUrl(domainUrl: String): String
}