package com.vaidyakhil.pixel.listing.model

import org.json.JSONObject

data class PixelMedia(
    val id: Int,
    val averageColor: String,
    val photographer: String,
    val photographerUrl: String,
    val src: MediaSrc
) {
    companion object {
        fun getParsedResponse (rawResponse: JSONObject): PixelMedia {
            return PixelMedia(
                id = rawResponse.optInt("id", 0),
                averageColor = rawResponse.optString("avg_color", ""),
                photographer = rawResponse.optString("photographer", ""),
                photographerUrl = rawResponse.optString("photographer_url", ""),
                src = MediaSrc.getParsedResponse(rawResponse.optJSONObject("src") ?: JSONObject())
            )
        }
    }
}
