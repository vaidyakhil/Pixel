package com.vaidyakhil.pixel.listing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vaidyakhil.pixel.databinding.ActivityListingBinding
import com.vaidyakhil.pixel.listing.repository.RepositoryImpl

class ListingActivity : AppCompatActivity() {
    private val TAG = "test"
    private val repositoryImpl: Contracts.Repository by lazy { RepositoryImpl(applicationContext) }
    private val viewModel: ListingViewModel by lazy {
        ViewModelProvider(
            this,
            ListingViewModelFactory(repositoryImpl)
        ).get(ListingViewModel::class.java)
    }

    private val adapter by lazy { MediaAdapter(this, viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.mainViewPager.adapter = adapter

        viewModel.mediaItems.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

    }
}