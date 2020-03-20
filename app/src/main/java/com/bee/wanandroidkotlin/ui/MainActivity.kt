package com.bee.wanandroidkotlin.ui

import android.content.Intent
import com.bee.baselibrary.ui.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.Constants
import com.bee.wanandroidkotlin.R

class MainActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.activity_main

    override fun initView() {
    }

    override fun initData(intent: Intent?) {
        toolBarBuilder.setTitle("主界面")
        val isLogin by Preference(Constants.SP_LOGIN, false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
