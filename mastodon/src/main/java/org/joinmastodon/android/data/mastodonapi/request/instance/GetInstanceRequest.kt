package org.joinmastodon.android.data.mastodonapi.request.instance

import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest
import org.joinmastodon.android.data.mastodonapi.response.EmojiResponse
import org.joinmastodon.android.data.mastodonapi.response.InstanceResponse
import org.joinmastodon.android.model.Instance

class GetInstanceRequest(private val mastodonApi: MastodonApi) {

    private fun buildUrl(domain: String) =
        "${MastodonRequest.HTTPS}$domain${MastodonEndpoints.INSTANCE.value}"

    fun getInstance(domain: String): MastodonRequest<InstanceResponse> {
        return MastodonRequest {
            mastodonApi.getInstance(buildUrl(domain))
        }
    }
}