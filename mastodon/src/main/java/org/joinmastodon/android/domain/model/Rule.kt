package org.joinmastodon.android.domain.model

data class Rule(
    var id: String? = null,
    var text: String? = null,
    @kotlin.jvm.Transient
    var parsedText: CharSequence? = null
)
