package com.bee.wanandroidkotlin.http.beans

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
                                   val collect: Boolean,
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
) {
    data class TagBean(val name: String, val url: String)
}

