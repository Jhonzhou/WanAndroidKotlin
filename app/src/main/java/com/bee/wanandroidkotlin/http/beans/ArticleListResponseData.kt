package com.bee.wanandroidkotlin.http.beans

import com.bee.wanandroidkotlin.beans.BaseTagTitleBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
data class ArticleListResponseData(val apkLink: String,
                                   val audit: Int,
                                   val author: String?,
                                   val canEdit: Boolean,
                                   val chapterId: Int,
                                   val chapterName: String?,
                                   var collect: Boolean,
                                   val courseId: Int,
                                   val desc: String,
                                   val descMd: String,
                                   val envelopePic: String,
                                   val fresh: Boolean,
                                   val id: Int,
                                   val link: String,
                                   val niceDate: String?,
                                   val niceShareDate: String,
                                   val origin: String,
                                   val originId: Int = -1,
                                   val prefix: String,
                                   val projectLink: String,
                                   val publishTime: Long,
                                   val selfVisible: Int,
                                   val shareDate: Long,
                                   val shareUser: String?,
                                   val superChapterId: Int,
                                   val superChapterName: String?,
                                   val tags: List<TagBean>?,
                                   val title: String?,
                                   val type: Int,
                                   val userId: Int,
                                   val visible: Int,
                                   val zan: Int
) : BaseTagTitleBean {
    override fun getTitleName(): String? {
        return title
    }

    data class TagBean(val name: String, val url: String)
}

