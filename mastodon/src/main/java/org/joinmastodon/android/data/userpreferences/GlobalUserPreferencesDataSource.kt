package org.joinmastodon.android.data.userpreferences

interface GlobalUserPreferencesDataSource {
    var playGifs: Boolean
    var useCustomTabs: Boolean
    var trueBlackTheme: Boolean
    var theme: ThemePreference
}