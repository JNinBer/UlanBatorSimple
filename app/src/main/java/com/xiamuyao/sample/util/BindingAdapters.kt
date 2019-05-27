package com.xiamuyao.sample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.xiamuyao.sample.App


object BindingAdapters {

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, url: String?) {
        Glide.with(App.CONTEXT).load(url).into(view)
    }

}