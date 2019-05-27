package com.xiamuyao.ulanbator.base.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.xiamuyao.ulanbator.R

/**
 * 错误信息页面
 */
class LoadErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_load_error
    }

    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}