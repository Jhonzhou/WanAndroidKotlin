package com.bee.wanandroidkotlin.ui.me

import android.content.Intent
import com.bee.baselibrary.base.BaseActivity

/**
 *
 * 收藏列表activity
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */
class MyCollectionDetailListActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = 0

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
    }

    override fun initData(intent: Intent?) {
        displayFragment(MyCollectionDetailListFragment())
    }

}