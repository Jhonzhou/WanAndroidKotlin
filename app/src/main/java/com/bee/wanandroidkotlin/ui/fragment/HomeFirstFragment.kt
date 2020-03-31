package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.utils.ToastAlone
import kotlinx.android.synthetic.main.fragment_home_first.*

/**
 *
 * 首页tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeFirstFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_first

    override fun initView() {
        toolBarBuilder.setTitle(R.string.s_home)
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.isVerticalScrollBarEnabled = true
        srlRefresh.setOnRefreshListener {
            ToastAlone.showToast("下拉刷新")
            tvTitle.text = "${tvTitle.text}+1"
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

}