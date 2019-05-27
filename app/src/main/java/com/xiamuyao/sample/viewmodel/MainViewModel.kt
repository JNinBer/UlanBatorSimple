package com.xiamuyao.sample.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.xiamuyao.ulanbator.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {


    var fragmentIndex = MutableLiveData<Int>()

    init {
        fragmentIndex.value = 0
    }

    override  fun initData() {

    }

    /**
     * 更改ViewPager下标
     * @param index Int
     */
    fun setViewPagerIndex(index: Int) {
        fragmentIndex.value = index
    }


}