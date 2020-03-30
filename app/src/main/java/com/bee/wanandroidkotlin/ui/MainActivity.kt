package com.bee.wanandroidkotlin.ui

import android.content.Intent
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.utils.ToastAlone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        toolBarBuilder.hideBackIcon()
        bottomNavigationView.itemIconTintList=null
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home ->{
                    it.isChecked=true
                    it.title="${it.title}+1"
                }
                R.id.navigation_project ->{
                    it.isChecked=true
                    it.title="${it.title}+1"
                }
                R.id.navigation_ground ->{
                    it.isChecked=true
                    it.title="${it.title}+1"
                }
                R.id.navigation_tencent ->{
                    it.isChecked=true
                    it.title="${it.title}+1"
                }
                R.id.navigation_me ->{
                    it.isChecked=true
                    it.title="${it.title}+1"
                }
            }
            ToastAlone.showToast(it.title.toString())
            true
        }
    }

    override fun initData(intent: Intent?) {
        toolBarBuilder.setTitle("主界面")
        val isLogin by Preference(Constants.SP.SP_LOGIN, false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
