package com.bee.wanandroidkotlin.ui.common.activity

import android.content.Intent
import com.bee.baselibrary.base.BaseActivity
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.common.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

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
    }

    override fun initListener() {
        super.initListener()
        openNext()
        tvTitle.setOnClickListener{
            openNext()
        }
    }
    private fun openNext(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}