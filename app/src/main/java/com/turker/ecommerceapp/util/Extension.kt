package com.turker.ecommerceapp.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.turker.ecommerceapp.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_image_placeholder).into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}