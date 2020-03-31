package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 * 首页广场Fragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeGroundFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_ground

    override fun initView() {
        toolBarBuilder.setTitle(R.string.s_ground)
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

}