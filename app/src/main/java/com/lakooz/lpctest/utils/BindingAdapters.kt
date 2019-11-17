package com.lakooz.lpctest.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.lakooz.lpctest.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url.isNullOrEmpty()) {
        imageView.setImageResource(R.drawable.ic_launcher_background)
    } else {
        Picasso.get().load(url).into(imageView,object :Callback{
            override fun onSuccess() {}

            override fun onError(e: Exception?) {
                imageView.setImageResource(R.drawable.ic_launcher_background)
            }
        })
    }
}