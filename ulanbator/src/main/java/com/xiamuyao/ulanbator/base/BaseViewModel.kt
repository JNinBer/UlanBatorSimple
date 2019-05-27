package com.xiamuyao.ulanbator.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xiamuyao.ulanbator.utlis.LibConstant
import com.xiamuyao.ulanbator.utlis.SingleLiveEvent
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

abstract class BaseViewModel(application: Application) : AndroidViewModel(application), KodeinAware {
    override val kodein by closestKodein(application)

    //上下文
    val context: Context = application
    //结束页面
    val finishStatus = SingleLiveEvent<Boolean>()
    //显示Dialog
    val showDialogStatus = SingleLiveEvent<Boolean>()
    //销毁Dialog
    val disDialogStatus = SingleLiveEvent<Boolean>()
    //上拉加载结束
    val finshLoadMoreStatus = SingleLiveEvent<Boolean>()
    //下拉刷新结束
    val finshRefreshingStatus = SingleLiveEvent<Boolean>()
    //加载状态
    var loadStatus = SingleLiveEvent<Int>()

    abstract fun initData()


    open fun <T> launch(block: suspend () -> T) = viewModelScope.launch {
        try {
            loadStatus.value = LibConstant.LOAD_ING
            block()
        } catch (t: Throwable) {
            loadStatus.value = LibConstant.LOAD_ERROR
            t.printStackTrace()
        }
    }

}