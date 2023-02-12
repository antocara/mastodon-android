package org.joinmastodon.android.data.oauth

import kotlinx.coroutines.flow.Flow
import org.joinmastodon.android.model.Token

interface OauthDataSource {
    suspend fun getOauthToken(code: String): Flow<Boolean>
}