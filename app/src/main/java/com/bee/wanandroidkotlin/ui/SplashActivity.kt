package com.bee.wanandroidkotlin.ui

import android.content.Intent
import com.bee.baselibrary.base.BaseActivity

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/19
 * @Description:
 */
class SplashActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = 0

    override fun initView() {
    }

    override fun initData(intent: Intent?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}