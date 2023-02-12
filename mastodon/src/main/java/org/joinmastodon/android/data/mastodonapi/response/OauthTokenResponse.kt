package org.joinmastodon.android.data.mastodonapi.response

import com.google.gson.annotations.SerializedName
import org.joinmastodon.android.model.Token

data class OauthTokenResponse(
    @SerializedName("access_token")
    var accessToken: String? = null,

    /**
     * The OAuth token type. Mastodon uses Bearer tokens.
     */
    @SerializedName("token_type")
    var tokenType: String? = null,

    /**
     * The OAuth scopes granted by this token, space-separated.
     */
    var scope: String? = null,

    /**
     * When the token was generated.
     * (unixtime)
     */
    @SerializedName("created_at")
    var createdAt: Long = 0
)

fun a(){
    val token = Token()
    token.accessToken = ""
}

fun OauthTokenResponse.toOldToken() :Token {
    val token = Token()
    token.accessToken = this.accessToken
    token.tokenType = this.tokenType
    token.scope = this.scope
    token.createdAt = this.createdAt
    return token
}
