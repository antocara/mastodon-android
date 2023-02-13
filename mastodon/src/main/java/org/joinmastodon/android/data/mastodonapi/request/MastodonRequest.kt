package org.joinmastodon.android.data.mastodonapi.request

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joinmastodon.android.data.mastodonapi.extensions.asResult
import retrofit2.Response

typealias ApiCall<T> = suspend () -> Response<T>

open class MastodonRequest<T>(val apiCallAction: ApiCall<T>) {

    companion object {
        const val HTTPS = "https://"
        const val JOIN_MASTODON_DOMAIN = "api.joinmastodon.org"
    }

    suspend fun execute(): T? {
        return withContext(Dispatchers.IO) {
            apiCallAction().asResult()
        }
    }
}