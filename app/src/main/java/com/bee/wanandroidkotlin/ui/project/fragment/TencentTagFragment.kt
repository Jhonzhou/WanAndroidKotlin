package com.bee.wanandroidkotlin.ui.project.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 * 主页 公众号tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class TencentTagFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_tencent

    override fun initView() {
        toolBarBuilder.setTitle(R.string.s_tencent)
    }

    override fun initData(arguments: Bundle?) {
    }

}