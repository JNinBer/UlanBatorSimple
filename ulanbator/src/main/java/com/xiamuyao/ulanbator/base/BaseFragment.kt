package com.xiamuyao.ulanbator.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.xiamuyao.ulanbator.utlis.*


abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    lateinit var binding: V

    lateinit var viewModel: VM

    var root: View? = null

    var mLoadService: LoadService<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)


        if (root != null) return mLoadService?.loadLayout

        binding = DataBindingUtil.inflate(
            inflater,
            initContentView(inflater, container, savedInstanceState),
            container,
            false
        )

        root = binding.root

        //加载 LoadSir
        mLoadService = LoadSir.getDefault().register(root) {
            viewModel.launch {
                viewModel.initData()
            }
        }

        viewModel = ViewModelProviders.of(this).get(initViewModel())
        //让ViewModel拥有View的生命周期感应
        binding.lifecycleOwner = this
        binding.setVariable(initVariableId(), viewModel)

        initView()

        lifecycleScope.launchWhenResumed {
            LL.d("加载了数据")
            //注册基本的事件回调
            initBaseLiveDataCallBack()
            initVVMObserver()
            loadData()
        }

        return mLoadService?.loadLayout

    }

    private fun initBaseLiveDataCallBack() {
        //页面状态修改
        viewModel.loadStatus.observe(this, Observer {
            LibBusinessUtli.switchPageState(it, mLoadService)
        })
    }


    /**
     * 初始化V-VM之间的观察者回调
     */
    abstract fun initVVMObserver()

    /**
     * 初始化部分点击时间或者 view事件
     */
    abstract fun initView()


    /**
     * 初始化页面数据
     */
    open fun loadData() {
        viewModel.initData()
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int

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