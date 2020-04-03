package com.bee.baselibrary

/**
 *
 * 错误状态
 * @author: JhonZhou
 * @date:  2020/4/3
 * @Description:
 */
enum class ErrorState {
    /**
     * 网络正常
     */
    CORRECT,
    /**
     * 网络错误
     */
    NET_ERROR,
    /**
     * 无数据
     */
    NO_DATA,
}