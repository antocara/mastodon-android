package org.joinmastodon.android.data.oauth.model

enum class GrantType(val value: String) {
    AUTHORIZATION_CODE("authorization_code"), CLIENT_CREDENTIALS("client_credentials")
}