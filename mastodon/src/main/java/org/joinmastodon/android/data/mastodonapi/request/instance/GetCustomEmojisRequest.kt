package org.joinmastodon.android.data.mastodonapi.request.instance

import org.joinmastodon.android.data.mastodonapi.MastodonApi
import org.joinmastodon.android.data.mastodonapi.MastodonEndpoints
import org.joinmastodon.android.data.mastodonapi.request.MastodonRequest
import org.joinmastodon.android.data.mastodonapi.response.EmojiResponse

class GetCustomEmojisRequest(private val mastodonApi: MastodonApi) {

    private fun buildUrl(domain: String) =
        "${MastodonRequest.HTTPS}$domain${MastodonEndpoints.CUSTOM_EMOJIS.value}"

    fun getEmojis(domain: String): MastodonRequest<List<EmojiResponse>> {
        return MastodonRequest {
            mastodonApi.getEmojis(buildUrl(domain))
        }
    }
}