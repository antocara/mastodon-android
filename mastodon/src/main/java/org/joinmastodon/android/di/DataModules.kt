package org.joinmastodon.android.di

import org.joinmastodon.android.data.GlobalUserPreferences
import org.joinmastodon.android.data.GlobalUserPreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModules = module {
    single<GlobalUserPreferencesDataSource> { GlobalUserPreferences(androidContext()) }
}