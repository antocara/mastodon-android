package org.joinmastodon.android.domain.model

data class MediaAttachmentsConfiguration(
    var supportedMimeTypes: List<String?>? = null,
    var imageSizeLimit: Int = 0,
    var imageMatrixLimit: Int = 0,
    var videoSizeLimit: Int = 0,
    var videoFrameRateLimit: Int = 0,
    var videoMatrixLimit: Int = 0
)
