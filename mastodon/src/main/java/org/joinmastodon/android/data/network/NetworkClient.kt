package org.joinmastodon.android.data.network


import okhttp3.OkHttpClient
import org.joinmastodon.android.data.network.interceptors.NetworkInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkClient(
    private val interceptors: List<NetworkInterceptor>?,
    private val baseUrl: String
) {

    fun build(): Retrofit {
        val okHttp = buildOkHttpClient(interceptors)
        return provideRetrofit(okHttp, baseUrl).build()
    }

    private fun buildOkHttpClient(interceptors: List<NetworkInterceptor>?): OkHttpClient {
        return OkHttpClientBuilder.build(interceptors = interceptors)
    }

    private fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }
}