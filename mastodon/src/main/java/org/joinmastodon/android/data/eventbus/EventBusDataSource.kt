package org.joinmastodon.android.data.eventbus

interface EventBusDataSource {

    fun post(event: Any)

    fun register(listener: Any)

    fun unregister(listener: Any)
}