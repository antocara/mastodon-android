package org.joinmastodon.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import me.grishka.appkit.imageloader.ImageCache
import me.grishka.appkit.utils.NetworkUtils
import me.grishka.appkit.utils.V
import org.joinmastodon.android.api.PushSubscriptionManager
import org.joinmastodon.android.di.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MusktodonApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
        context = applicationContext
        V.setApplicationContext(context)
        val params = ImageCache.Parameters()
        params.diskCacheSize = 100 * 1024 * 1024
        params.maxMemoryCacheSize = Int.MAX_VALUE
        ImageCache.setParams(params)
        NetworkUtils.setUserAgent("MastodonAndroid/" + BuildConfig.VERSION_NAME)
        PushSubscriptionManager.tryRegisterFCM()
    }

    companion object {
        @JvmField
        @SuppressLint("StaticFieldLeak") // it's not a leak
        var context: Context? = null
    }

    private fun initializeKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MusktodonApp)
            modules(dataModules)
        }
    }
}