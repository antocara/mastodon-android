package org.joinmastodon.android.data.mastodonapi.response

import android.text.TextUtils
import com.google.gson.annotations.SerializedName
import java.net.IDN
import java.util.Locale
import me.grishka.appkit.imageloader.requests.UrlImageLoaderRequest
import me.grishka.appkit.utils.V
import org.joinmastodon.android.api.ObjectValidationException
import org.joinmastodon.android.domain.model.Region

data class CatalogInstanceResponse(
    var domain: String? = null,
    var version: String? = null,
    var description: String? = null,
    var languages: List<String?>? = null,
    @SerializedName("region")
    private val _region: String? = null,
    var categories: List<String>? = null,
    var proxiedThumbnail: String? = null,
    var totalUsers: Int = 0,
    var lastWeekUsers: Int = 0,
    var approvalRequired: Boolean = false,
    var language: String? = null,
    var category: String? = null,

    @Transient
    var region: Region? = null,

    @Transient
    var normalizedDomain: String? = null,

    @Transient
    var thumbnailRequest: UrlImageLoaderRequest? = null
) {

    //TODO
    @Throws(ObjectValidationException::class)
    fun postprocess() {

        normalizedDomain =
            if (domain!!.startsWith("xn--") || domain!!.contains(".xn--")) {
                IDN.toUnicode(domain)
            } else {
                domain
            }
        if (!TextUtils.isEmpty(_region)) {
            try {
                region = Region.valueOf(_region!!.uppercase(Locale.getDefault()))
            } catch (ignore: IllegalArgumentException) {
            }
        }
        if (!TextUtils.isEmpty(proxiedThumbnail)) {
            thumbnailRequest = UrlImageLoaderRequest(proxiedThumbnail, 0, V.dp(56f))
        }
    }
}
