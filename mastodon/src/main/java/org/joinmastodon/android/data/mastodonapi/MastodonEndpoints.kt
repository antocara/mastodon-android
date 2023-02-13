package org.joinmastodon.android.data.mastodonapi

const val PREFIX_API_VERSION = "/api/v1"

enum class MastodonEndpoints(val value: String) {

    OAUTH_TOKEN("/oauth/token"),
    OWN_ACCOUNT("$PREFIX_API_VERSION/accounts/verify_credentials"),
    CUSTOM_EMOJIS("$PREFIX_API_VERSION/custom_emojis"),
    INSTANCE("$PREFIX_API_VERSION/instance"),
    CATEGORIES("/categories"),
    SERVERS("/servers")
}