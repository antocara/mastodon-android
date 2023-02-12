package org.joinmastodon.android.data.mastodonapi.request

import com.google.gson.annotations.SerializedName
import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.data.oauth.model.GrantType

data class OauthTokenRequest(
    @SerializedName("client_id")
    var clientId: String,
    @SerializedName("client_secret")
    var clientSecret: String,
    var code: String,
    @SerializedName("grant_type")
    var grantType: String,
    @SerializedName("redirect_uri")
    var redirectUri: String = AccountSessionManager.REDIRECT_URI,
    var scope: String = AccountSessionManager.SCOPE
) {
    constructor(
        clientID: String,
        clientSecret: String,
        code: String,
        grantType: String
    ) : this(clientId = clientID, clientSecret = clientSecret, code = code, grantType = grantType)
}