package com.xiamuyao.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xiamuyao.sample.BR
import com.xiamuyao.sample.databinding.FragmentHomeBinding
import com.xiamuyao.sample.viewmodel.HomeViewModel
import com.xiamuyao.ulanbator.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun initView() {

    }

    override fun initVVMObserver() {



//        LL.d(viewModel.user.value.toString())
//        // 页面view生成之后调用数据
//        //
//        //
//        //
//        lifecycleScope.launch {
//            whenResumed { }
//        }


    }

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return com.xiamuyao.sample.R.layout.fragment_home
    }

    override fun initVariableId(): Int {
        return BR.homeViewModel
    }

    override fun initViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

}