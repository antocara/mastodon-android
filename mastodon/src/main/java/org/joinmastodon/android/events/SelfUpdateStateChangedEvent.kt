package org.joinmastodon.android.events

import org.joinmastodon.android.updater.GithubSelfUpdater.UpdateState

class SelfUpdateStateChangedEvent(val state: UpdateState)