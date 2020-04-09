package com.bee.wanandroidkotlin.ui.me

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 * 首页 我的tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeMeFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_me

    override fun initView() {
        toolBarBuilder.setTitle(R.string.s_me)
    }

    override fun initData(arguments: Bundle?) {
    }


}