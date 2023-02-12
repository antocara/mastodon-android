package org.joinmastodon.android.data.mastodonapi

enum class MastodonEndpoints(val value: String) {
    OAUTH_TOKEN("/oauth/token"),
    OWN_ACCOUNT("/api/v1/accounts/verify_credentials")
}