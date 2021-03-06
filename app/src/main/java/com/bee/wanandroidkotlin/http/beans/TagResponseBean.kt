package com.bee.wanandroidkotlin.http.beans

import com.bee.wanandroidkotlin.beans.BaseTagTitleBean
import java.io.Serializable

/**
 *
 *  项目tab数据
 * @author: JhonZhou
 * @date:  2020/4/7
 * @Description:
 */
data class TagResponseBean(val children: List<TagResponseBean>?,
                           val courseId: Int,
                           val id: Int,
                           val name: String?,
                           val order: Int,
                           val parentChapterId: Int,
                           val userControlSetTop: Boolean,
                           val visible: Int) : Serializable, BaseTagTitleBean {
    override fun getTitleName(): String? {
        return name
    }
}