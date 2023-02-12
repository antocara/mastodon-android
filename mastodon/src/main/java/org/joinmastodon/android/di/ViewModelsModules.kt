package org.joinmastodon.android.di

import org.joinmastodon.android.features.oauth.OAuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  viewModels = module {
    viewModel { OAuthViewModel(get()) }
}