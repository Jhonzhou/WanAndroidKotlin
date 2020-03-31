package com.bee.wanandroidkotlin.http.beans

/**
 *  主页banner接口返回数据
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
data class HomeBannerResponse(var id: Int,
                              var desc: String,
                              var imagePath: String,
                              var isVisible: Int = 0,
                              var order: Int = 0,
                              var title: String,
                              var type: Int = 0,
                              var url: String)