package com.bee.wanandroidkotlin.constants

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/23
 * @Description:
 */
object HttpConstants {
    /**
     * 网络基础地址
     */
    const val BASE_URL = "https://wanandroid.com/"
    /**
     * 登录
     */
    const val LOGIN: String = "/user/login"
    /**
     * 注册
     */
    const val REGISTER: String = "/user/register"

    /**
     * 首页数据
     */
    const val HOME_LIST = "/article/list/{page}/json"
    /**
     * 置顶列表
     */
    const val TOP_LIST = "/article/top/json"
    /**
     * 首页banner
     */
    const val HOME_BANNER = "/banner/json"
    /**
     * 搜索
     */
    const val SEARCH = "/article/query/{page}/json"
    /**
     * 项目分类
     */
    const val PROJECT_TAB = "/project/tree/json"
    /**
     * 项目分类列表详情
     */
    const val PROJECT_TAB_DETAIL_LIST = "project/list/{page}/json"
    /**
     * 体系数据
     */
    const val TREE_TAG = "/tree/json"
    /**
     * 体系对应列表
     */
    const val TREE_DETAIL_LIST = "/article/list/{page}/json"

    /**
     * 导航数据
     */
    const val NAVIGATION_LIST = "/navi/json"
    /**
     * 公众号列表
     */
    const val TENCENT_LIST = "/wxarticle/chapters/json"
    /**
     * 公众号详情列表
     */
    const val TENCENT_DETAIL_LIST = "wxarticle/list/{cid}/{page}/json"


}