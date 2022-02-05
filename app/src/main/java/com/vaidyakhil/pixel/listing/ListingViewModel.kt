package com.vaidyakhil.pixel.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vaidyakhil.pixel.listing.model.PixelMedia

class ListingViewModel(private val repository: Contracts.Repository) : ViewModel(),
    Contracts.MediaProvider {
    private val TAG = "test"

    private var nextPage = 1

    private val _mediaItems = MutableLiveData<ArrayList<PixelMedia>>()
    val mediaItems: LiveData<ArrayList<PixelMedia>>
        get() = _mediaItems

    private val _showSpinner = MutableLiveData<Boolean>(true)
    val showSpinner: LiveData<Boolean>
        get() = _showSpinner

    init {
        Log.d(TAG, "init: ${Log.d(TAG, "getData: ${Thread.currentThread().id}")}")
        getData()
    }

    private fun getData() {
        _showSpinner.value = true
        Thread {
            Log.d(TAG, "getData: ${Thread.currentThread().id}")
            repository.getMedia(nextPage, object : Contracts.NetworkCallBack {
                override fun onSuccess(media: ArrayList<PixelMedia>, _nextPage: Int?) {
                    _showSpinner.postValue(false)
                    _mediaItems.postValue(media)
                    nextPage = _nextPage ?: nextPage
                }

                override fun onError(message: String?) {
                    _showSpinner.postValue(false)
                    Log.d(TAG, "onError: $message")
                }
            })
        }.start()

    }

    override fun getMediaLength(): Int {
        return mediaItems.value?.size ?: 0
    }
}