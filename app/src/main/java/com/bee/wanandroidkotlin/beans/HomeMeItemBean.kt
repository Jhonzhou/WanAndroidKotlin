package com.bee.wanandroidkotlin.beans

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
data class HomeMeItemBean(
        val title: String,
        var isShowTag: Boolean = false,
        val rightText: String = "",
        var isShowDivider: Boolean = true,
        var itemClickListener: (() -> Unit)? = null
) {
}