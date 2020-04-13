package com.bee.wanandroidkotlin.beans

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
data class HomeMeItemBean(
        var id: ID,
        val title: String,
        var isShowTag: Boolean = false,
        val rightText: String = "",
        var isShowDivider: Boolean = true
) {
    enum class ID {
        //收藏
        COLLECT,
        //积分
        INTEGRAL,
        //文章
        ARTICLE,
        //设置
        SETTING,
    }
}