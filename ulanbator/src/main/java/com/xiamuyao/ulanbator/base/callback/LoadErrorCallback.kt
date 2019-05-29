package com.xiamuyao.ulanbator.base.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.xiamuyao.ulanbator.R
import android.widget.Toast
import com.xiamuyao.ulanbator.utlis.LL

/**
 * 错误信息页面
 */
class LoadErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_load_error
    }

    //是否在显示Callback视图的时候显示原始图(SuccessView)，返回true显示，false隐藏
    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    //将Callback添加到当前视图时的回调，View为当前Callback的布局View
    override fun onAttach(context: Context?, view: View?) {
        super.onAttach(context, view)
    }


}