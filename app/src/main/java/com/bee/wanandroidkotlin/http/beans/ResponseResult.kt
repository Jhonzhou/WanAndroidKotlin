package com.bee.wanandroidkotlin.http.beans

import com.bee.wanandroidkotlin.utils.ToastAlone

/**
 *
 * 接口请求返回来
 * @author: JhonZhou
 * @date:  2020/3/24
 * @Description:
 */
data class ResponseResult<T>(var errorCode: Int, var errorMsg: String?, var data: T? = null) {
    companion object {
        const val CODE_EXCEPTION = -10001
        const val CODE_SUCCESS = 0
        const val CODE_HTTP_CODE_ERROR = -10002
        const val CODE_CANCEL = -10003
    }

    /**
     * 判断网络请求是否成功
     */
     fun isSuccessful(): Boolean = errorCode == CODE_SUCCESS

    /**
     * 是否是发生异常
     */
     fun isException(): Boolean = errorCode == CODE_EXCEPTION

     fun isCancel(): Boolean = errorCode == CODE_CANCEL

    fun handlerResult(
            errorBlock: (result: ResponseResult<T>) -> Unit = {
                ToastAlone.showToast(it.errorMsg)
            },
            successBlock: (result: ResponseResult<T>) -> Unit
    ) {
        when {
            isSuccessful() -> successBlock(this)
            isCancel() -> {
            }
            else -> errorBlock(this)
        }
    }
}
