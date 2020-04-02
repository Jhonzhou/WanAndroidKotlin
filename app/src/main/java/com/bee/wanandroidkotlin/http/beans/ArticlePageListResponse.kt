package com.bee.wanandroidkotlin.http.beans

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
data class ArticlePageListResponse(
        val curPage: Int,
        val datas: ArrayList<ArticleListResponseData>?,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)