package org.joinmastodon.android.data.mastodonapi

import org.joinmastodon.android.data.mastodonapi.request.OauthTokenBody
import org.joinmastodon.android.data.mastodonapi.request.OauthTokenRequest
import org.joinmastodon.android.data.mastodonapi.response.CatalogCategoryResponse
import org.joinmastodon.android.data.mastodonapi.response.CatalogInstanceResponse
import org.joinmastodon.android.data.mastodonapi.response.EmojiResponse
import org.joinmastodon.android.data.mastodonapi.response.InstanceResponse
import org.joinmastodon.android.data.mastodonapi.response.OauthTokenResponse
import org.joinmastodon.android.data.mastodonapi.response.OwnAccountResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface MastodonApi {
    ///// OAUTH ////
    @POST
    suspend fun getOauthToken(
        @Url url: String,
        @Body body: OauthTokenBody
    ): Response<OauthTokenResponse>

    @GET
    suspend fun getOwnAccount(
        @Header("Authorization") accessToken: String,
        @Url url: String
    ): Response<OwnAccountResponse>

    ///// INSTANCE ////
    @GET
    suspend fun getEmojis(
        @Url url: String
    ): Response<List<EmojiResponse>>

    @GET
    suspend fun getInstance(
        @Url url: String
    ): Response<InstanceResponse>

    ///// CATALOG ////
    @GET
    suspend fun getCatalogCategories(
        @Url url: String
    ): Response<List<CatalogCategoryResponse>>

    @GET
    suspend fun getCatalogInstances(
        @Url url: String
    ): Response<List<CatalogInstanceResponse>>
}