package com.bee.wanandroidkotlin.http.beans

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
data class HomePageListResponse(
        val curPage: Int,
        val datas: List<HomePageListResponseData>?,
        val offset: Int,
        val over: Boolean ,
        val pageCount: Int,
        val size: Int,
        val total: Int
)