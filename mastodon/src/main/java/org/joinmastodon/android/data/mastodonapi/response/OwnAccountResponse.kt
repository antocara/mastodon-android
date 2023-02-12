package org.joinmastodon.android.data.mastodonapi.response

import android.text.TextUtils
import java.time.Instant
import java.time.LocalDate
import org.joinmastodon.android.api.ObjectValidationException
import org.joinmastodon.android.api.RequiredField
import org.joinmastodon.android.model.Account
import org.joinmastodon.android.model.AccountField
import org.joinmastodon.android.model.BaseModel
import org.joinmastodon.android.model.Emoji
import org.joinmastodon.android.model.Source

data class OwnAccountResponse(
    // Base attributes
    /**
     * The account id
     */
    @RequiredField
    var id: String? = null,

    /**
     * The username of the account, not including domain.
     */
    @RequiredField
    var username: String? = null,

    /**
     * The Webfinger account URI. Equal to username for local users, or username@domain for remote users.
     */
    @RequiredField
    var acct: String? = null,

    /**
     * The location of the user's profile page.
     */
    @RequiredField
    var url: String? = null,
    // Display attributes
    /**
     * The profile's display name.
     */
    @RequiredField
    var displayName: String? = null,

    /**
     * The profile's bio / description.
     */
    @RequiredField
    var note: String? = null,

    /**
     * An image icon that is shown next to statuses and in the profile.
     */
    @RequiredField
    var avatar: String? = null,

    /**
     * A static version of the avatar. Equal to avatar if its value is a static image; different if avatar is an animated GIF.
     */
    var avatarStatic: String? = null,

    /**
     * An image banner that is shown above the profile and in profile cards.
     */
    @RequiredField
    var header: String? = null,

    /**
     * A static version of the header. Equal to header if its value is a static image; different if header is an animated GIF.
     */
    var headerStatic: String? = null,

    /**
     * Whether the account manually approves follow requests.
     */
    var locked: Boolean = false,

    /**
     * Custom emoji entities to be used when rendering the profile. If none, an empty array will be returned.
     */
    var emojis: List<Emoji>? = null,

    /**
     * Whether the account has opted into discovery features such as the profile directory.
     */
    var discoverable: Boolean = false,
    // Statistical attributes
    /**
     * When the account was created.
     */
    @RequiredField
    var createdAt: Instant? = null,

    /**
     * When the most recent status was posted.
     */
    //	@RequiredField
    var lastStatusAt: LocalDate? = null,

    /**
     * How many statuses are attached to this account.
     */
    var statusesCount: Long = 0,

    /**
     * The reported followers of this profile.
     */
    var followersCount: Long = 0,

    /**
     * The reported follows of this profile.
     */
    var followingCount: Long = 0,
    // Optional attributes
    /**
     * Indicates that the profile is currently inactive and that its user has moved to a new account.
     */
    var moved: Account? = null,

    /**
     * Additional metadata attached to a profile as name-value pairs.
     */
    var fields: List<AccountField>? = null,

    /**
     * A presentational flag. Indicates that the account may perform automated actions, may not be monitored, or identifies as a robot.
     */
    var bot: Boolean = false,

    /**
     * An extra entity to be used with API methods to verify credentials and update credentials.
     */
    var source: Source? = null,

    /**
     * An extra entity returned when an account is suspended.
     */
    var suspended: Boolean = false,

    /**
     * When a timed mute will expire, if applicable.
     */
    var muteExpiresAt: Instant? = null
) {

    // @Throws(ObjectValidationException::class)
    // override fun postprocess() {
    //     super.postprocess()
    //     if (fields != null) {
    //         for (f in fields!!) f.postprocess()
    //     }
    //     if (emojis != null) {
    //         for (e in emojis!!) e.postprocess()
    //     }
    //     if (moved != null) moved.postprocess()
    //     if (TextUtils.isEmpty(displayName)) displayName = username
    // }

    fun isLocal(): Boolean {
        return !acct!!.contains("@")
    }

    fun getDomain(): String? {
        val parts = acct!!.split("@".toRegex(), limit = 2).toTypedArray()
        return if (parts.size == 1) null else parts[1]
    }

    fun getDisplayUsername(): String {
        return "@$acct"
    }

    override fun toString(): String {
        return "Account{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", acct='" + acct + '\'' +
            ", url='" + url + '\'' +
            ", displayName='" + displayName + '\'' +
            ", note='" + note + '\'' +
            ", avatar='" + avatar + '\'' +
            ", avatarStatic='" + avatarStatic + '\'' +
            ", header='" + header + '\'' +
            ", headerStatic='" + headerStatic + '\'' +
            ", locked=" + locked +
            ", emojis=" + emojis +
            ", discoverable=" + discoverable +
            ", createdAt=" + createdAt +
            ", lastStatusAt=" + lastStatusAt +
            ", statusesCount=" + statusesCount +
            ", followersCount=" + followersCount +
            ", followingCount=" + followingCount +
            ", moved=" + moved +
            ", fields=" + fields +
            ", bot=" + bot +
            ", source=" + source +
            ", suspended=" + suspended +
            ", muteExpiresAt=" + muteExpiresAt +
            '}'
    }
}

fun OwnAccountResponse.toOld(): Account {
    val account = Account()
    account.id = this.id
    account.username = this.username
    account.acct = this.acct
    account.url = this.url
    account.displayName = this.displayName
    account.note = this.note
    account.avatar = this.avatar
    account.avatarStatic = this.avatarStatic
    account.header = this.header
    account.headerStatic = this.headerStatic
    account.locked = this.locked
    account.emojis = this.emojis
    account.discoverable = this.discoverable
    account.createdAt = this.createdAt
    account.lastStatusAt = this.lastStatusAt
    account.statusesCount = this.statusesCount
    account.followersCount = this.followersCount
    account.followingCount = this.followingCount
    account.moved = this.moved
    account.fields = this.fields
    account.bot = this.bot
    account.source = this.source
    account.suspended = this.suspended
    account.muteExpiresAt = this.muteExpiresAt
    return account
}