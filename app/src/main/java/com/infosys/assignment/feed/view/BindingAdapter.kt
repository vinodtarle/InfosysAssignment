package com.infosys.assignment.feed.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.infosys.assignment.R

@BindingAdapter("imageView")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .thumbnail(0.5f)
            .apply(
                RequestOptions()
                    .override(120, 70)
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            ).into(view)
    }
}