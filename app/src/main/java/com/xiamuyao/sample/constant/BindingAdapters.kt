package com.xiamuyao.sample.constant

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager

object BindingAdapters {

    @BindingAdapter("nowCurrentTab")
    @JvmStatic
    fun setViewPagerCurrentTab(view: ViewPager, index: Int) {
        view.currentItem = index
    }

}