package org.joinmastodon.android.data.mastodonapi.extensions

import retrofit2.Response

fun <T> Response<T>.asResult(): T? {
    if (this.isSuccessful) {
        return this.body()
    } else {
        throw RequestMastodonApiException()
    }
}

class RequestMastodonApiException() : Exception()