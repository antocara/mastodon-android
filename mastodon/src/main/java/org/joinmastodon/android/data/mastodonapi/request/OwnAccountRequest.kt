package org.joinmastodon.android.data.mastodonapi.request

import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest.Companion.HTTPS
import org.joinmastodon.android.data.mastodonapi.response.OwnAccountResponse
import org.joinmastodon.android.di.mastodonApiModule
import org.joinmastodon.android.model.Instance
import org.joinmastodon.android.model.Token

class OwnAccountRequest(private val mastodonApi: MastodonApi) {

    companion object {
        private const val BEARER = "Bearer "
    }

    private fun buildUrl(domainUrl: String): String {
        return "$HTTPS$domainUrl${MastodonEndpoints.OWN_ACCOUNT.value}"
    }

    private fun buildBearerHeader(token: Token): String {
        return (BEARER.plus(token.accessToken))
    }

    fun getAccount(token: Token, instance: Instance): MastodonRequest<OwnAccountResponse> {

        return MastodonRequest {
            mastodonApi.getOwnAccount(buildBearerHeader(token), buildUrl(instance.uri))
        }
    }
}
