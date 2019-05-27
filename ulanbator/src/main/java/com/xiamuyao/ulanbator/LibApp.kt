package com.xiamuyao.ulanbator

import android.annotation.SuppressLint
import android.content.Context
import com.jeremyliao.liveeventbus.LiveEventBus
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadSir
import com.xiamuyao.ulanbator.base.callback.LoadErrorCallback
import com.xiamuyao.ulanbator.base.callback.LoadIngCallback
import com.xiamuyao.ulanbator.base.callback.LoadNoNetCallback
import com.xiamuyao.ulanbator.base.callback.LoadNullCallback

@SuppressLint("StaticFieldLeak")
object LibApp {
    private var context: Context? = null


    fun init(c: Context) {

        context = c

        setLoadSir()

        setLiveDataBus()
    }

    fun setLiveDataBus() {

        LiveEventBus.get()
            .config()
            .supportBroadcast(context)
            .lifecycleObserverAlwaysActive(true)

    }

    fun getContext(): Context {
        return context!!
    }

    fun setLoadSir(
        LoadIngCallback: Callback = LoadIngCallback(),
        LoadErrorCallback: Callback = LoadErrorCallback(),
        LoadNoNetCallback: Callback = LoadNoNetCallback(),
        LoadNullCallback: Callback = LoadNullCallback()
    ) {
        LoadSir.beginBuilder()
            .addCallback(LoadIngCallback)
            .addCallback(LoadErrorCallback)
            .addCallback(LoadNoNetCallback)
            .addCallback(LoadNullCallback)
            .setDefaultCallback(LoadIngCallback::class.java)
            .commit()
    }
}