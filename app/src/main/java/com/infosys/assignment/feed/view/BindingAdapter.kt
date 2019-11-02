package com.infosys.assignment.feed.view

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


/*
* BindingAdapter for bind Image on ImageView using binding
* with Glide lib to lezzy loading
*
* */
@BindingAdapter("imageView")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.visibility = View.GONE
        Glide.with(view.context)
            .load(url)
            .thumbnail(0.5f)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    view.visibility = View.VISIBLE
                    return false
                }
            })
            .apply(
                RequestOptions()
                    .override(120, 70)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            ).into(view)
    }
}