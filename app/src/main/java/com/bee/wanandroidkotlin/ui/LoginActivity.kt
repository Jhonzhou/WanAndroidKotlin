package com.bee.wanandroidkotlin.ui

import android.content.Intent
import com.bee.baselibrary.ui.BaseActivity
import com.bee.wanandroidkotlin.R

/**
 *
 *  登录界面
 * @author: JhonZhou
 * @date:  2020/3/20
 * @Description:
 */
class LoginActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        toolBarBuilder.setTitle("登录")
    }

    override fun initData(intent: Intent?) {
    }

}