package com.xiamuyao.sample.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewpager.widget.ViewPager
import com.xiamuyao.sample.BR
import com.xiamuyao.sample.R
import com.xiamuyao.sample.adapter.SectionsPagerAdapter
import com.xiamuyao.sample.databinding.ActivityMainBinding
import com.xiamuyao.sample.fragment.FindFragment
import com.xiamuyao.sample.fragment.HomeFragment
import com.xiamuyao.sample.fragment.MessageFragment
import com.xiamuyao.sample.fragment.MyFragment
import com.xiamuyao.sample.viewmodel.MainViewModel
import com.xiamuyao.ulanbator.base.BaseActivity
import com.xiamuyao.ulanbator.base.BaseFragment
import com.xiamuyao.ulanbator.base.BaseViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), ViewPager.OnPageChangeListener,
    View.OnClickListener {

    private val mFragmentList: Array<BaseFragment<out ViewDataBinding, out BaseViewModel>> by lazy {
        arrayOf(HomeFragment(), FindFragment(), MessageFragment(), MyFragment())
    }

    private val markerAdapter by lazy {
        SectionsPagerAdapter(supportFragmentManager, mFragmentList)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mainBottomTabOne -> {
                binding.viewPager.currentItem = 0
            }
            R.id.mainBottomTabTwo -> {
                binding.viewPager.currentItem = 1

            }
            R.id.mainBottomTabThere -> {
                binding.viewPager.currentItem = 2

            }
            R.id.mainBottomTabFour -> {
                binding.viewPager.currentItem = 3
            }
        }
    }

    override fun initView() {
        binding.include2.mainBottomTabOne.setOnClickListener(this)
        binding.include2.mainBottomTabTwo.setOnClickListener(this)
        binding.include2.mainBottomTabThere.setOnClickListener(this)
        binding.include2.mainBottomTabFour.setOnClickListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun initVVMObserver() {

    }

    override fun initData() {
        with(binding.viewPager) {
            adapter = markerAdapter
            currentItem = 4
            addOnPageChangeListener(this@MainActivity)
        }
    }


    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initVariableId(): Int {
        return BR.mainViewModel
    }

    override fun initViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, MainActivity::class.java)
            // starter.putExtra()
            context.startActivity(starter)
        }
    }
}
