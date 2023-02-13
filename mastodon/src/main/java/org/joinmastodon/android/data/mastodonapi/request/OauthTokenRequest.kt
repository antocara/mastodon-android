package org.joinmastodon.android.data.mastodonapi.request

import com.google.gson.annotations.SerializedName
import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.extensions.asResult
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest.Companion.HTTPS
import org.joinmastodon.android.data.mastodonapi.response.OauthTokenResponse
import org.joinmastodon.android.data.oauth.model.GrantType
import org.joinmastodon.android.model.Application
import org.joinmastodon.android.model.Instance

class OauthTokenRequest(private val mastodonApi: MastodonApi) {

    private fun buildUrl(domain: String) = "$HTTPS$domain${MastodonEndpoints.OAUTH_TOKEN.value}"

    suspend fun getToken(
        application: Application,
        instance: Instance,
        code: String
    ): MastodonRequest<OauthTokenResponse> {
        val body = OauthTokenBody(
            application.clientId,
            application.clientSecret,
            code,
            GrantType.AUTHORIZATION_CODE.value
        )
        return MastodonRequest {
            mastodonApi.getOauthToken(buildUrl(instance.uri), body)
        }
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