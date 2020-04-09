package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R

/**
 *
 *  问答
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class HomeAnswerFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_answer
    override fun initView() {
        toolBarBuilder.setTitle("问答")
    }

    override fun initData(arguments: Bundle?) {
    }

}