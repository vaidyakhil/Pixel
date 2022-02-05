package com.vaidyakhil.pixel.listing.model

import org.json.JSONArray
import org.json.JSONObject
import utils.map

data class PixelResponse(
    val page: Int,
    val perPage: Int,
    val nextPage: Int?,
    val prevPage: Int?,
    val media: ArrayList<PixelMedia>
) {
    companion object {
        fun getParsedResponse (rawResponse: JSONObject): PixelResponse {
            return PixelResponse(
                page = rawResponse.optInt("page", 0),
                perPage = rawResponse.optInt("per_page", 4),
                nextPage = rawResponse.optInt("next_page", 1),
                prevPage = rawResponse.optInt("prev_page", 0),
                media = (rawResponse.optJSONArray("photos") ?: JSONArray()).map {
                    PixelMedia.getParsedResponse(it)
                }
            )
        }
    }
}