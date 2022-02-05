package com.vaidyakhil.pixel.listing

import com.vaidyakhil.pixel.listing.model.PixelMedia


interface Contracts {
    interface MediaProvider {
        fun getMediaLength(): Int
    }

    interface NetworkCallBack {
        fun onSuccess(media: ArrayList<PixelMedia>, nextPage: Int?)
        fun onError(message: String?)
    }

    interface Repository {
        fun getMedia(currPage: Int, callback: NetworkCallBack)
    }


}