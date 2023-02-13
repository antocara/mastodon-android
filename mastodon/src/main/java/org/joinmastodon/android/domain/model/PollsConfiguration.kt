package org.joinmastodon.android.domain.model

data class PollsConfiguration(
    var maxOptions: Int = 0,
    var maxCharactersPerOption: Int = 0,
    var minExpiration: Int = 0,
    var maxExpiration: Int = 0
)
