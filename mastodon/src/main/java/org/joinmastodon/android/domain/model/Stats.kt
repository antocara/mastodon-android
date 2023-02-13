package org.joinmastodon.android.domain.model

data class Stats(
    var userCount: Int = 0,
    var statusCount: Int = 0,
    var domainCount: Int = 0,
)
