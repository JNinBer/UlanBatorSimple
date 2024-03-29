package com.xiamuyao.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xiamuyao.sample.BR
import com.xiamuyao.sample.R
import com.xiamuyao.sample.databinding.FragmentMessageBinding
import com.xiamuyao.sample.viewmodel.MessageViewModel
import com.xiamuyao.ulanbator.base.BaseFragment


class MessageFragment : BaseFragment<FragmentMessageBinding, MessageViewModel>() {
    override fun initView() {
    }

    override fun initVVMObserver() {
    }


    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_message
    }

    override fun initVariableId(): Int {
        return BR.messageViewModel
    }

    override fun initViewModel(): Class<MessageViewModel> {
        return MessageViewModel::class.java
    }

}