package com.xiamuyao.sample.activity

import android.animation.Animator
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.xiamuyao.sample.BR
import com.xiamuyao.sample.R
import com.xiamuyao.sample.databinding.ActivityLaunchBinding
import com.xiamuyao.sample.viewmodel.LaunchViewModel
import com.xiamuyao.ulanbator.base.BaseActivity
import com.xiamuyao.ulanbator.utlis.LL
import com.xiamuyao.ulanbator.utlis.LibConstant
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LaunchActivity : BaseActivity<ActivityLaunchBinding, LaunchViewModel>() {

    override fun initView() {

        lifecycleScope.launch {
            delay(2 * 1000)
            MainActivity.start(this@LaunchActivity)
        }

//        animation_view.addAnimatorListener(object : Animator.AnimatorListener {
//            override fun onAnimationRepeat(animation: Animator?) {
//                LL.d("onAnimationRepeat")
//            }
//
//            override fun onAnimationEnd(animation: Animator?) {
//                LL.d("onAnimationEnd")
//            }
//
//            override fun onAnimationCancel(animation: Animator?) {
//                LL.d("onAnimationCancel")
//
//            }
//
//            override fun onAnimationStart(animation: Animator?) {
//                LL.d("onAnimationStart")
//            }
//
//        })

    }

    override fun initVVMObserver() {
    }


    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_launch
    }

    override fun initVariableId(): Int {
        return BR.launchViewModel
    }

    override fun initViewModel(): Class<LaunchViewModel> {
        return LaunchViewModel::class.java
    }

}