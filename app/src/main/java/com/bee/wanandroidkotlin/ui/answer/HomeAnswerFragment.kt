package com.bee.wanandroidkotlin.ui.answer

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
    override fun getContentLayoutId(): Int = R.layout.common_refresh_and_recycleview
    override fun initView() {
        toolBarBuilder.setTitle("问答")
    }

    override fun initData(arguments: Bundle?) {
    }

}