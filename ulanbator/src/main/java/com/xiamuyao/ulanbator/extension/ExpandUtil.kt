package com.xiamuyao.ulanbator.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiamuyao.ulanbator.base.adapter.BaseListAdapter
import com.xiamuyao.ulanbator.base.BaseViewModel
import com.xiamuyao.ulanbator.net.BaseResponse
import com.xiamuyao.ulanbator.utlis.LibConstant

/**
 * 通用拓展函数
 */

/**
 * 列表默认 Adapter 和 LayoutManager 设置
 * @receiver RecyclerView
 * @param mAdapter BaseListAdapter<T>
 * @param LayoutManager RecyclerView.LayoutManager?
 */
fun <T> RecyclerView.defaultStyle(
    mAdapter: BaseListAdapter<T>,
    LayoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(context) as RecyclerView.LayoutManager
) {
    this.adapter = mAdapter
    this.layoutManager = LayoutManager
}

fun <T> BaseViewModel.businessHandler(data: BaseResponse<T>): BaseResponse<T> {
    //这里的错误代码 根据和后台约定重新建立常量类
    when (data.errorCode) {
        0 -> { //业务正常
            this.loadStatus.value = LibConstant.LOAD_OK
        }
        1 -> { //密码错误

        }
        else -> { //加载错误 未捕捉错误
            this.loadStatus.value = LibConstant.LOAD_ERROR
        }
    }
    return data
}