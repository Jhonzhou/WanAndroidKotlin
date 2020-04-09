package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 * 导航 fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class NavigationTabFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_navigation_tab

    override fun initView() {
        toolBarBuilder.setTitle("导航")
    }

    override fun initData(arguments: Bundle?) {
    }

}