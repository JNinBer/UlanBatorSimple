package com.xiamuyao.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xiamuyao.sample.BR
import com.xiamuyao.sample.R
import com.xiamuyao.sample.databinding.FragmentFindBinding
import com.xiamuyao.sample.viewmodel.FindViewModel
import com.xiamuyao.ulanbator.base.BaseFragment


class FindFragment : BaseFragment<FragmentFindBinding, FindViewModel>() {
    override fun initView() {

    }

    override fun initVVMObserver() {
    }

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_find
    }

    override fun initVariableId(): Int {
        return BR.findViewModel
    }

    override fun initViewModel(): Class<FindViewModel> {
        return FindViewModel::class.java
    }

}