package com.vaidyakhil.pixel.listing.model

import org.json.JSONObject

data class MediaSrc(
    val original: String,
    val large: String,
    val small: String,
    val medium: String,
) {
    companion object {
        fun getParsedResponse (rawResponse: JSONObject): MediaSrc {
            return MediaSrc(
                original =  rawResponse.optString("original"),
                large =  rawResponse.optString("large"),
                small =  rawResponse.optString("small"),
                medium =  rawResponse.optString("medium"),
            )
        }
    }
}