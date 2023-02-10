package org.joinmastodon.android.data

import android.content.Context
import android.content.SharedPreferences
import org.joinmastodon.android.MastodonApp

class GlobalUserPreferences : GlobalUserPreferencesDataSource {

    companion object {
        private const val GLOBAL_PREFERENCES_NAME = "global"
        private const val PLAY_GIFS_KEY = "playGifs"
        private const val USE_CUSTOM_TABS_KEY = "useCustomTabs"
        private const val TRUE_BLACK_THEME_KEY = "trueBlackTheme"
        private const val THEME_KEY = "theme"
    }

    private val prefs: SharedPreferences
        get() = MastodonApp.context.getSharedPreferences(
            GLOBAL_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

    override var playGifs: Boolean
        get() = prefs.getBoolean(PLAY_GIFS_KEY, true)
        set(value) {
            prefs.edit()
                .putBoolean(PLAY_GIFS_KEY, value).apply()
        }

    override var useCustomTabs: Boolean
        get() = prefs.getBoolean(USE_CUSTOM_TABS_KEY, true)
        set(value) {
            prefs.edit()
                .putBoolean(USE_CUSTOM_TABS_KEY, value).apply()
        }

    override var trueBlackTheme: Boolean
        get() = prefs.getBoolean(TRUE_BLACK_THEME_KEY, false)
        set(value) {
            prefs.edit()
                .putBoolean(TRUE_BLACK_THEME_KEY, value).apply()
        }

    override var theme: ThemePreference
        get() = ThemePreference.values()[prefs.getInt(THEME_KEY, 0)]
        set(value) {
            prefs.edit()
                .putInt(THEME_KEY, value.ordinal).apply()
        }
}