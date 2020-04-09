package com.bee.wanandroidkotlin.http.beans

import java.io.Serializable

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
data class NavigationResponseBean(val articles: List<ArticleListResponseData>?,
                                  val cid: Int,
                                  val name: String?) : Serializable


