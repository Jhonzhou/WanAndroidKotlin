package com.bee.wanandroidkotlin.ui.activity

import android.content.Intent
import androidx.fragment.app.Fragment
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.ui.fragment.*
import com.bee.wanandroidkotlin.utils.ToastAlone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.activity_main
    private var currentFragment: Fragment? = null
    private val fragmentList: ArrayList<Fragment> by lazy {
        val list = arrayListOf<Fragment>()
        list.add(HomeFirstFragment())
        list.add(HomeProjectFragment())
        list.add(HomeGroundFragment())
        list.add(HomeTencentFragment())
        list.add(HomeMeFragment())
        list
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        bottomNavigationView.itemIconTintList = null
        showFragment(0)
    }

    override fun initListener() {
        super.initListener()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            val position: Int
            when (it.itemId) {
                R.id.navigation_home -> {
                    position = 0
                }
                R.id.navigation_project -> {
                    position = 1
                }
                R.id.navigation_ground -> {
                    position = 2
                }
                R.id.navigation_tencent -> {
                    position = 3
                }
                R.id.navigation_me -> {
                    position = 4
                }
                else -> {
                    position = 0
                }
            }
            showFragment(position)
            ToastAlone.showToast(it.title.toString())
            true
        }
    }

    private fun showFragment(position: Int) {
        val selectFragment = fragmentList[position]
        if (selectFragment == currentFragment) {
            return
        }
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let {
                if (!it.isHidden) {
                    hide(it)
                }
            }
            if (!selectFragment.isAdded) {
               add(R.id.flContainer, selectFragment)
            }
            show(selectFragment)
            currentFragment = selectFragment
        }.commit()
    }

    override fun initData(intent: Intent?) {
        val isLogin by Preference(Constants.SP.SP_LOGIN, false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
