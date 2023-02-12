package org.joinmastodon.android.data.network


import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.time.Instant
import java.time.LocalDate
import okhttp3.OkHttpClient
import org.joinmastodon.android.api.gson.IsoInstantTypeAdapter
import org.joinmastodon.android.api.gson.IsoLocalDateTypeAdapter
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
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
    }

    private fun buildGson(): Gson{
        return GsonBuilder()
            .disableHtmlEscaping()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Instant::class.java, IsoInstantTypeAdapter())
            .registerTypeAdapter(LocalDate::class.java, IsoLocalDateTypeAdapter())
            .create()
    }
}