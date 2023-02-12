package org.joinmastodon.android.data.mastodonapi.request

import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest.Companion.HTTPS
import org.joinmastodon.android.model.Token

class OwnAccountRequest {

    companion object {
        private const val BEARER = "Bearer "
        fun url(domainUrl: String): String {
            return "$HTTPS$domainUrl${MastodonEndpoints.OWN_ACCOUNT.value}"
        }

        fun buildBearerHeader(token: Token): String {
            return (BEARER.plus(token.accessToken))
        }
    }
}
