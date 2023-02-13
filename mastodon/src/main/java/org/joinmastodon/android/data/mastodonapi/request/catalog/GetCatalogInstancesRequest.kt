package org.joinmastodon.android.data.mastodonapi.request.catalog

import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest
import org.joinmastodon.android.data.mastodonapi.response.CatalogCategoryResponse
import org.joinmastodon.android.data.mastodonapi.response.CatalogInstanceResponse

class GetCatalogInstancesRequest(private val mastodonApi: MastodonApi) {

    private fun buildUrl(language: String, category: String): String {
        //TODO use Uri.builder
        return "${MastodonRequest.HTTPS}${MastodonRequest.JOIN_MASTODON_DOMAIN}" +
            "${MastodonEndpoints.SERVERS.value}?language=$language&category=$category"
    }

    fun getCatalogInstance(
        language: String,
        category: String
    ): MastodonRequest<List<CatalogInstanceResponse>> {
        return MastodonRequest {
            mastodonApi.getCatalogInstances(buildUrl(language, category))
        }
    }
}