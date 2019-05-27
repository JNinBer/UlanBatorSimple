package com.xiamuyao.ulanbator.base.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.xiamuyao.ulanbator.R

/**
 * 没有网络页面
 */
class LoadNoNetCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_load_no_net
    }

    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}