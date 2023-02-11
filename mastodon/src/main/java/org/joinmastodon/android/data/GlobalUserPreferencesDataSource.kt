package org.joinmastodon.android.data

interface GlobalUserPreferencesDataSource {
    var playGifs: Boolean
    var useCustomTabs: Boolean
    var trueBlackTheme: Boolean
    var theme: ThemePreference
}