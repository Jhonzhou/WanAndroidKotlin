package com.bee.wanandroidkotlin.ui.activity

import android.content.Intent
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/19
 * @Description:
 */
class SplashActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = R.layout.activity_splash

    override fun initView() {
    }

    override fun initData(intent: Intent?) {
        toolBarBuilder.hideCommonBaseTitle()
        val isLogin by Preference(Constants.SP.SP_LOGIN, false)
        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()

    }

}