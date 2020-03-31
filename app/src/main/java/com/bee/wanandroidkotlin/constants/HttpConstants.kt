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
     * 首页banner
     */
    const val HOME_BANNER = "/banner/json"


}