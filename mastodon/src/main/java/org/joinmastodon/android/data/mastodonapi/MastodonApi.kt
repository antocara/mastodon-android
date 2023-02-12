package org.joinmastodon.android.data.mastodonapi

import org.joinmastodon.android.data.mastodonapi.request.OauthTokenRequest
import org.joinmastodon.android.data.mastodonapi.response.OauthTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface MastodonApi {

    @POST
    suspend fun getOauthToken(
        @Url url: String,
        @Body body: OauthTokenRequest
    ): Response<OauthTokenResponse>
}