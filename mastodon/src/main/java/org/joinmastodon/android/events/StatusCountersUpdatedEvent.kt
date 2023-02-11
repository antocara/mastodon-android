package org.joinmastodon.android.events

import org.joinmastodon.android.model.Status

class StatusCountersUpdatedEvent(status: Status) {
    @JvmField
	var id: String
    @JvmField
	var favorites: Long
    @JvmField
	var reblogs: Long
    @JvmField
	var replies: Long
    @JvmField
	var favorited: Boolean
    @JvmField
	var reblogged: Boolean
    @JvmField
	var bookmarked: Boolean

    init {
        id = status.id
        favorites = status.favouritesCount
        reblogs = status.reblogsCount
        replies = status.repliesCount
        favorited = status.favourited
        reblogged = status.reblogged
        bookmarked = status.bookmarked
    }
}