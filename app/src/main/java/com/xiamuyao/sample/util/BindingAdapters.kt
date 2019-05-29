package com.xiamuyao.sample.util

import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.xiamuyao.sample.App

object BindingAdapters {

    @BindingAdapter("nowCurrentTab")
    @JvmStatic
    fun setViewPagerCurrentTab(view: ViewPager, index: Int) {
        view.currentItem = index
    }

    @BindingAdapter("netImage")
    @JvmStatic
    fun setImageViewWithNetWord(view: ImageView, url: String) {
        Glide.with(App.CONTEXT)
            .load(url)
            .into(view)
    }
}