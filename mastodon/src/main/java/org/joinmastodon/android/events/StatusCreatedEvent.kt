package org.joinmastodon.android.events

import org.joinmastodon.android.model.Status

class StatusCreatedEvent(val status: Status, val accountID: String)