package org.joinmastodon.android.events

class RemoveAccountPostsEvent(
    val accountID: String,
    val postsByAccountID: String,
    val isUnfollow: Boolean
)