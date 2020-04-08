package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 * 体系 fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class SystemTabFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_system_tab

    override fun initView() {
        toolBarBuilder.setTitle("体系")
    }

    override fun initData(arguments: Bundle?) {
    }

}