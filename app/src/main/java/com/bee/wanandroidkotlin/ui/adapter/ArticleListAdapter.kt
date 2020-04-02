package com.bee.wanandroidkotlin.ui.adapter

import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.wanandroidkotlin.http.beans.HomePageListResponseData
import com.bee.wanandroidkotlin.ui.adapter.delegate.HomePageListDelegate

/**
 *
 * 文章列表 适配器
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
class ArticleListAdapter : BaseRvAdapter<HomePageListResponseData>() {
    init {
        addDelegate(HomePageListDelegate())
    }
}