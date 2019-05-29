package com.xiamuyao.ulanbator.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.xiamuyao.ulanbator.utlis.*


abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    lateinit var binding: V
    lateinit var viewModel: VM
    lateinit var mContext: Context

    open val loadPage: Boolean = true

    val mLoadService by lazy {
        LoadSir.getDefault().register(this) {
            initData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        //页面传参
        initParam()
        //私有的初始化 Data Binding 和 ViewModel 方法
        initViewDataBinding(savedInstanceState)
        // View 初始化
        initView()
        //初始化数据
        initData()
        //初始化 V <--> VM LiveData 改变 V
        initVVMObserver()
        //注册基本的事件回调
        initBaseLiveDataCallBack()
    }


    private fun initBaseLiveDataCallBack() {

        viewModel.disDialogStatus.observe(this, Observer {
            dismissDialog()
        })

        viewModel.showDialogStatus.observe(this, Observer {
            showDialog()
        })

        viewModel.loadStatus.observe(this, Observer {
            LibBusinessUtli.switchPageState(it, mLoadService)
        })

        viewModel.finishStatus.observe(this, Observer { finish() })

    }

    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))

        viewModel = ViewModelProviders.of(this).get(initViewModel())
        //让 ViewModel 拥有View的生命周期感应
        binding.setVariable(initVariableId(), viewModel)
        binding.lifecycleOwner = this
    }


    private fun showDialog() {
        LL.d("showDialog")
    }

    private fun dismissDialog() {
        LL.d("dismissDialog")
    }

    /**
     *  接受上一个界面传递的参数
     */
    open fun initParam() {

    }

    /**
     * 初始化V-VM之间的观察者回调
     */
    abstract fun initVVMObserver()

    /**
     * 初始化页面数据
     */
    open fun initData() {
        viewModel.initData()
    }

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(savedInstanceState: Bundle?): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * initialize The ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    abstract fun initViewModel(): Class<VM>


}