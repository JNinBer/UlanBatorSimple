package com.xiamuyao.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xiamuyao.sample.BR
import com.xiamuyao.sample.R
import com.xiamuyao.sample.databinding.FragmentHomeBinding
import com.xiamuyao.sample.model.bean.Dataa
import com.xiamuyao.sample.viewmodel.HomeViewModel
import com.xiamuyao.ulanbator.base.BaseFragment
import com.xiamuyao.ulanbator.base.adapter.BaseNoChildClickAdapter
import com.xiamuyao.ulanbator.extension.defaultStyle
import com.xiamuyao.ulanbator.utlis.LibConstant


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    val homeListAdapter by lazy {
        BaseNoChildClickAdapter(R.layout.item_home, viewModel.itemList, BR.itemHome)
    }

    override fun initView() {
        binding.homeRecyclerView.defaultStyle(homeListAdapter)
    }

    override fun initVVMObserver() {

    }

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_home
    }

    override fun initVariableId(): Int {
        return BR.homeViewModel
    }

    override fun initViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

}