package com.bee.wanandroidkotlin.http.beans

/**
 * 登录接口返回对象
 * @author: JhonZhou
 * @date:  2020/3/24
 * @Description:
 */
data class LoginResponseBean(var id: Int,
                             var username: String,
                             var password: String,
                             var icon: String?,
                             var type: Int,
                             var collectIds: List<Int>?)