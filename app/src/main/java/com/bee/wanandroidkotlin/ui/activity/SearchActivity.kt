package com.bee.wanandroidkotlin.ui.activity

import android.content.Intent
import com.bee.baselibrary.base.BaseActivity
import com.bee.wanandroidkotlin.ui.fragment.SearchFragment

/**
 *
 *  搜索activity
 * @author: JhonZhou
 * @date:  2020/4/2
 * @Description:
 */
class SearchActivity : BaseActivity() {
    override fun getContentLayoutId(): Int = 0

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        displayFragment(SearchFragment())
    }

    override fun initData(intent: Intent?) {
    }

}