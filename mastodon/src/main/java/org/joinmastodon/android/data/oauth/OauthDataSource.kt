package org.joinmastodon.android.data.oauth

import org.joinmastodon.android.model.Token

interface OauthDataSource {
    suspend fun getOauthToken(code: String): Boolean
}