package com.vaidyakhil.pixel.listing

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vaidyakhil.pixel.R
import com.vaidyakhil.pixel.databinding.FragmentMediaBinding

class MediaFragment : Fragment() {

    companion object {
        const val POSITION_KEY = "position"
    }

    private val DEFAULT_AVG_COLOR = "#BEAE64"
    private val viewModel: ListingViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            ListingViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = arguments?.getInt(POSITION_KEY, 0)
        val binding = FragmentMediaBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        val mediaData = viewModel.mediaItems.value?.get(position ?: 0)
        binding.mediaData = mediaData

        binding.root.background = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            getBackgroundColors(mediaData?.averageColor)
        )

        binding.photographerName.setTextColor(getTextColor(mediaData?.averageColor))
        binding.providerMetadata.setTextColor(getTextColor(mediaData?.averageColor))
        return binding.root
    }

    private fun getTextColor (avgColor: String?): Int {
        val color = avgColor?:DEFAULT_AVG_COLOR

        val avgRed = Color.parseColor(color).red
        val avgGreen = Color.parseColor(color).green
        val avgBlue = Color.parseColor(color).blue

        return Color.argb(0xff, avgRed, avgGreen, avgBlue)
    }

    private fun getBackgroundColors(avgColor: String?): IntArray {
        val color = avgColor?:DEFAULT_AVG_COLOR

        val avgRed = Color.parseColor(color).red
        val avgGreen = Color.parseColor(color).green
        val avgBlue = Color.parseColor(color).blue

        val startRed = if (avgRed > 33) avgRed - 33 else avgRed
        val startGreen = if (avgGreen > 33) avgGreen - 33 else avgGreen
        val startBlue = if (avgBlue > 33) avgBlue - 33 else avgBlue

        val endRed = if (avgRed < 222) avgRed + 33 else avgRed
        val endGreen = if (avgGreen < 222) avgGreen + 33 else avgGreen
        val endBlue = if (avgBlue < 222) avgBlue + 33 else avgBlue

        val alpha = 33
        val startColor = Color.argb(alpha, startRed, startGreen, startBlue)
        val endColor = Color.argb(alpha, endRed, endGreen, endBlue)
        return intArrayOf(startColor, endColor)
    }
}