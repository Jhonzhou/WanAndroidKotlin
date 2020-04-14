package com.bee.wanandroidkotlin.http.beans

import com.bee.wanandroidkotlin.beans.BaseTagTitleBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */
data class HotTagResponseBean(var id: Int,
                              var name: String,
                              var link: Any,
                              var visible: Int,
                              var order: Int) : BaseTagTitleBean {
    override fun getTitleName(): String? = name
}