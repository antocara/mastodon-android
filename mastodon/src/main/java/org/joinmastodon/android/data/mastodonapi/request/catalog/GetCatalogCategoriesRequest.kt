package org.joinmastodon.android.data.mastodonapi.request.catalog


import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest
import org.joinmastodon.android.data.mastodonapi.response.CatalogCategoryResponse

class GetCatalogCategoriesRequest(private val mastodonApi: MastodonApi) {

    private fun buildUrl(language: String): String {
        //TODO use Uri.builder
        return "${MastodonRequest.HTTPS}${MastodonRequest.JOIN_MASTODON_DOMAIN}" +
            "${MastodonEndpoints.CATEGORIES.value}?language=$language"
    }

    fun getCatalogCategories(language: String): MastodonRequest<List<CatalogCategoryResponse>> {
        return MastodonRequest {
            mastodonApi.getCatalogCategories(buildUrl(language))
        }
    }
}