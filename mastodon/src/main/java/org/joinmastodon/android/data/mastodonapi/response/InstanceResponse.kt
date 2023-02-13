package org.joinmastodon.android.data.mastodonapi.response

import android.text.Html
import java.net.IDN
import org.joinmastodon.android.domain.model.Configuration
import org.joinmastodon.android.domain.model.Rule
import org.joinmastodon.android.model.Account
import org.joinmastodon.android.model.Instance
import org.joinmastodon.android.model.catalog.CatalogInstance

class InstanceResponse(
    /**
     * The domain name of the instance.
     */

    var uri: String? = null,
    /**
     * The title of the website.
     */

    var title: String? = null,

    /**
     * Admin-defined description of the Mastodon site.
     */

    var description: String? = null,

    /**
     * A shorter description defined by the admin.
     */

    var shortDescription: String? = "",

    /**
     * An email that may be contacted for any inquiries.
     */

    var email: String? = null,

    /**
     * The version of Mastodon installed on the instance.
     */

    var version: String? = null,

    /**
     * Primary languages of the website and its staff.
     */

    var languages: List<String>? = null,

    /**
     * Whether registrations are enabled.
     */
    var registrations: Boolean = false,

    /**
     * Whether registrations require moderator approval.
     */
    var approvalRequired: Boolean = false,

    /**
     * Whether invites are enabled.
     */
    var invitesEnabled: Boolean = false,

    /**
     * URLs of interest for clients apps.
     */
    var urls: Map<String, String>? = null,

    /**
     * Banner image for the website.
     */
    var thumbnail: String? = null,

    /**
     * A user that can be contacted, as an alternative to email.
     */
    var contactAccount: Account? = null,
    var stats: Instance.Stats? = null,

    var rules: List<Rule>? = emptyList(),
    var configuration: Configuration? = null,

    // non-standard field in some Mastodon forks
    var maxTootChars: Int = 0

)

fun InstanceResponse.toCatalogInstance(): CatalogInstance {
    return CatalogInstance().apply {
        domain = uri
        normalizedDomain = IDN.toUnicode(uri)
        description = Html.fromHtml(shortDescription).toString().trim { it <= ' ' }

        this@toCatalogInstance.languages?.let {
            language = languages?.first()
            languages = this@toCatalogInstance.languages
        } ?: apply {
            languages = emptyList()
            language = "unknown"
        }

        proxiedThumbnail = thumbnail

        stats?.let {
            totalUsers = it.userCount
        }

    }
}
