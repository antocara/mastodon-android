package org.joinmastodon.android.data.mastodonapi.request


interface MastodonRequest {

    companion object {
        const val HTTPS = "https://"
    }

    fun buildUrl(domainUrl: String): String
}