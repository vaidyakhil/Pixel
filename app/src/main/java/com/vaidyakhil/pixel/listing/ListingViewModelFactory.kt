package com.vaidyakhil.pixel.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListingViewModelFactory(private val repository: Contracts.Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListingViewModel(repository) as T
    }
}