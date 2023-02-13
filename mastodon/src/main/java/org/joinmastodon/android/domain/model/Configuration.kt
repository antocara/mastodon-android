package org.joinmastodon.android.domain.model

data class Configuration(
    var statuses: StatusesConfiguration? = null,
    var mediaAttachments: MediaAttachmentsConfiguration? = null,
    var polls: PollsConfiguration? = null
)
