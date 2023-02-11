package org.joinmastodon.android.data.eventbus

import com.squareup.otto.AsyncBus

object EventBus : EventBusDataSource {

    private val bus = AsyncBus()


    override fun post(event: Any) {
        bus.post(event)
    }

    override fun register(listener: Any) {
        bus.register(listener)
    }

    override fun unregister(listener: Any) {
        bus.unregister(listener)
    }
}