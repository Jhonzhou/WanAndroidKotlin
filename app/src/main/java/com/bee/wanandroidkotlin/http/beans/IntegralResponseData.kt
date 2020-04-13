package com.bee.wanandroidkotlin.http.beans

import java.io.Serializable

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
data class IntegralResponseData(
        var coinCount: Int = 0,
        var rank: Int = 0,
        var userId: Int? = 0,
        var username: String = ""
) : Serializable