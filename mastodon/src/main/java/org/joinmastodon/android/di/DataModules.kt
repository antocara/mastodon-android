package org.joinmastodon.android.di

import org.joinmastodon.android.api.session.AccountSessionManager
import org.joinmastodon.android.data.oauth.OauthDataSource
import org.joinmastodon.android.data.oauth.OauthRepository
import org.joinmastodon.android.data.userpreferences.GlobalUserPreferences
import org.joinmastodon.android.data.userpreferences.GlobalUserPreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModules = module {
    single<GlobalUserPreferencesDataSource> { GlobalUserPreferences(androidContext()) }

    single<OauthDataSource> { OauthRepository(AccountSessionManager.getInstance()) }
}