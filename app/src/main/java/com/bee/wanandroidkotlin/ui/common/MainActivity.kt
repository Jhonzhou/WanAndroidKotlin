package com.bee.wanandroidkotlin.ui.common

import android.content.Intent
import androidx.fragment.app.Fragment
import com.bee.baselibrary.base.BaseActivity
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.answer.HomeAnswerFragment
import com.bee.wanandroidkotlin.ui.ground.fragment.HomeGroundFragment
import com.bee.wanandroidkotlin.ui.home.fragment.HomeFirstFragment
import com.bee.wanandroidkotlin.ui.me.HomeMeFragment
import com.bee.wanandroidkotlin.ui.project.fragment.HomeProjectFragment
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
        list.add(HomeAnswerFragment())
        list.add(HomeMeFragment())
        list
    }

    override fun initData(intent: Intent?) {
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
                R.id.navigation_answer -> {
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


    private var lastPressTime = 0L
    override fun onBackPressed() {
        val currentPressTime = System.currentTimeMillis()
        if (currentPressTime - lastPressTime > 2000) {
            ToastAlone.showToast("再按一次退出")
            lastPressTime = currentPressTime
            return
        } else {
            lastPressTime = 0L
            //打开主界面等同按下home键  虚假退出
//            val mIntent = Intent()
//            mIntent.action = Intent.ACTION_MAIN
//            mIntent.addCategory(Intent.CATEGORY_HOME)
//            startActivity(mIntent)

            super.onBackPressed()
        }

    }

}
