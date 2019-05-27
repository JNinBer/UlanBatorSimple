package com.xiamuyao.ulanbator.base.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.xiamuyao.ulanbator.R

/**
 * 等待中页面
 */
class LoadIngCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_load_ing
    }

    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}