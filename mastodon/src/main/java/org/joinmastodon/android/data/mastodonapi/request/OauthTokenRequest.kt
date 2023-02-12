package org.joinmastodon.android.data.mastodonapi.request

import com.google.gson.annotations.SerializedName
import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.extensions.asResult
import org.joinmastodon.android.data.mastodonapi.response.OauthTokenResponse

class OauthTokenRequest(apiClient: MastodonApi) :
    MastodonRequest<OauthTokenBody, OauthTokenResponse>(apiClient) {

    override suspend fun execute(domainUrl: String, body: OauthTokenBody): OauthTokenResponse? {
        return runCatching {
            apiClient.getOauthToken(buildUrl(domainUrl), body = body).asResult()
        }.getOrNull()
    }

    override fun buildUrl(domainUrl: String): String {
        return "$HTTPS$domainUrl${MastodonEndpoints.OAUTH_TOKEN.value}"
    }
}

data class OauthTokenBody(
    @SerializedName("client_id") var clientId: String,
    @SerializedName("client_secret") var clientSecret: String,
    var code: String,
    @SerializedName("grant_type") var grantType: String,
    @SerializedName("redirect_uri") var redirectUri: String = AccountSessionManager.REDIRECT_URI,
    var scope: String = AccountSessionManager.SCOPE
) {
    constructor(
        clientID: String, clientSecret: String, code: String, grantType: String
    ) : this(clientId = clientID, clientSecret = clientSecret, code = code, grantType = grantType)
}