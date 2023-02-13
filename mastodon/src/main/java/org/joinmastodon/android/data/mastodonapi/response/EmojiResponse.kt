package org.joinmastodon.android.data.mastodonapi.response

import org.joinmastodon.android.api.RequiredField

class EmojiResponse(
    /**
     * The name of the custom emoji.
     */

    var shortcode: String? = null,
    /**
     * A link to the custom emoji.
     */

    var url: String? = null,

    /**
     * A link to a static copy of the custom emoji.
     */

    var staticUrl: String? = null,

    /**
     * Whether this Emoji should be visible in the picker or unlisted.
     */

    var visibleInPicker: Boolean = false,

    /**
     * Used for sorting custom emoji in the picker.
     */
    var category: String? = null,

    ) {
    override fun toString(): String {
        return "Emoji{" +
            "shortcode='" + shortcode + '\'' +
            ", url='" + url + '\'' +
            ", staticUrl='" + staticUrl + '\'' +
            ", visibleInPicker=" + visibleInPicker +
            ", category='" + category + '\'' +
            '}'
    }
}
