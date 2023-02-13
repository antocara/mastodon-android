package org.joinmastodon.android.domain.model

data class StatusesConfiguration(
    var maxCharacters: Int = 0,
    var maxMediaAttachments: Int = 0,
    var charactersReservedPerUrl: Int = 0
)
