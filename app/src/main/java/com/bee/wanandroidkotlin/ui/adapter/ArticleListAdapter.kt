package com.bee.wanandroidkotlin.ui.adapter

import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.adapter.delegate.ArticleListItemDelegate

/**
 *
 * 文章列表 适配器
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
class ArticleListAdapter : BaseRvAdapter<ArticleListResponseData>() {
    init {
        addDelegate(ArticleListItemDelegate())
    }
}