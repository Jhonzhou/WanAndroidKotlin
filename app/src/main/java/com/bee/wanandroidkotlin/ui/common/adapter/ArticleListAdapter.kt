package com.bee.wanandroidkotlin.ui.common.adapter

import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.common.adapter.delegate.ArticleListItemDelegate

/**
 *
 * 文章列表 适配器
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */

class ArticleListAdapter : BaseRvAdapter<ArticleListResponseData>() {
    init {
        addDelegate(ArticleListItemDelegate(fun(item: ArticleListResponseData, position: Int) {
            collectClickListener?.let {
                it(item, position)
            }
        }))
    }

    private var collectClickListener: ((ArticleListResponseData, Int) -> Unit?)? = null

    fun setCollectClickListener(clickListener: (item: ArticleListResponseData, position: Int) -> Unit) {
        collectClickListener = clickListener
    }
}