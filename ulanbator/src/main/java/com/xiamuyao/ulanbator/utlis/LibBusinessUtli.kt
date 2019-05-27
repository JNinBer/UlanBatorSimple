package com.xiamuyao.ulanbator.utlis

import com.kingja.loadsir.core.LoadService
import com.xiamuyao.ulanbator.base.callback.LoadErrorCallback
import com.xiamuyao.ulanbator.base.callback.LoadIngCallback
import com.xiamuyao.ulanbator.base.callback.LoadNullCallback

object LibBusinessUtli {

    /**
     * 页面状态切换
     * @param it Int?
     */
    fun switchPageState(it: Int?, mLoadService: LoadService<*>?) {
        when (it) {
            LibConstant.LOAD_OK -> {
                mLoadService?.showSuccess()
            }
            LibConstant.LOAD_NULL -> {
                mLoadService?.showCallback(LoadNullCallback::class.java)
            }
            LibConstant.LOAD_ING -> {
                mLoadService?.showCallback(LoadIngCallback::class.java)
            }
            LibConstant.LOAD_ERROR -> {
                mLoadService?.showCallback(LoadErrorCallback::class.java)
            }
        }
    }
}