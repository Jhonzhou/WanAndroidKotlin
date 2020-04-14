package com.bee.wanandroidkotlin.listener

/**
 *
 *  通用回调
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */
interface CommonCallBack<T> {
    fun onSuccess(result: T, code: Int)
    fun onError(message: String, code: Int)
}