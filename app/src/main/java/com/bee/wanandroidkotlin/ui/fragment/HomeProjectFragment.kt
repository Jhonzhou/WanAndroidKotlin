package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 *  主页 项目tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeProjectFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_project

    override fun initView() {
        toolBarBuilder.setTitle(R.string.s_project)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

}