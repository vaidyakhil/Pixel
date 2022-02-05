package utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vaidyakhil.pixel.R
import com.vaidyakhil.pixel.listing.model.MediaSrc

@BindingAdapter("mediaSrc")
fun loadImage(view: ImageView, mediaSrc: MediaSrc) {
    val requestOptions = RequestOptions().apply {
        placeholder(R.drawable.default_image)
        error(R.drawable.default_image)
    }
    Glide
        .with(view)
        .setDefaultRequestOptions(
            requestOptions
        )
        .load(
            mediaSrc.original
        )
        .thumbnail(
            Glide
                .with(view)
                .load(
                    mediaSrc.small
                )
        )
        .into(view)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}



