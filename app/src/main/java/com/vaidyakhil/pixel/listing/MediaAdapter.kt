package com.vaidyakhil.pixel.listing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MediaAdapter(
    fragmentActivity: FragmentActivity,
    private val mediaProvider: Contracts.MediaProvider
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return mediaProvider.getMediaLength()
    }

    override fun createFragment(position: Int): Fragment {
        return MediaFragment().apply {
            arguments = Bundle().apply {
                putInt(MediaFragment.POSITION_KEY, position)
            }
        }
    }
}