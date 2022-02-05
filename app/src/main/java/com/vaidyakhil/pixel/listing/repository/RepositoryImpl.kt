package com.vaidyakhil.pixel.listing.repository

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.vaidyakhil.pixel.listing.Contracts
import com.vaidyakhil.pixel.listing.model.PixelResponse
import network.NetworkOperationManager
import org.json.JSONObject

class RepositoryImpl(private val context: Context) : Contracts.Repository {

    private val baseUrl = "https://api.pexels.com/"
    private val curatedMediaEndPoint = "v1/curated"
    private val perPage = 8

    private fun getPixelMediaRequest(
        currPage: Int,
        callback: Contracts.NetworkCallBack
    ): JsonObjectRequest {
        val queryParams = "?page=$currPage&per_page=$perPage"
        val url = baseUrl + curatedMediaEndPoint + queryParams
        return PixelMediaRequest(
            url,
            {
                val pixelMediaResponse = PixelResponse.getParsedResponse(it)
                callback.onSuccess(pixelMediaResponse.media, pixelMediaResponse.nextPage)
            },
            {
                callback.onError(it.message)
            }
        )
    }

    override fun getMedia(currPage: Int, callback: Contracts.NetworkCallBack) {
        NetworkOperationManager.getInstance(context)
            .addToRequestQueue(getPixelMediaRequest(currPage, callback))
    }

    private class PixelMediaRequest(
        url: String,
        successListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener,
    ) : JsonObjectRequest(
        Method.GET,
        url,
        null,
        successListener,
        errorListener
    ) {
        /*
        **  create a dev acoount at and use your api key to run
        **  https://www.pexels.com/api/
         */
        private val API_KEY = "USE-YOUR-OWN-PEXEL-API :p"

        override fun getHeaders(): MutableMap<String, String> {
            val res = mutableMapOf<String, String>()
            res["Authorization"] = API_KEY
            return res
        }
    }
}