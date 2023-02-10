package org.joinmastodon.android.events

import org.joinmastodon.android.model.Poll

class PollUpdatedEvent(var accountID: String, var poll: Poll)