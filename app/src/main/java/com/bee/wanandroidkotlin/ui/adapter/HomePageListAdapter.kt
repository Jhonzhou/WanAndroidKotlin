package com.bee.wanandroidkotlin.ui.adapter

import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.wanandroidkotlin.http.beans.HomePageListResponseData
import com.bee.wanandroidkotlin.ui.adapter.delegate.HomePageListDelegate

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
class HomePageListAdapter : BaseRvAdapter<HomePageListResponseData>() {
    init {
        addDelegate(HomePageListDelegate())
    }
}