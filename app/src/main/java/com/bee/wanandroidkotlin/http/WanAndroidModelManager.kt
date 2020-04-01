package com.bee.wanandroidkotlin.http

import com.bee.wanandroidkotlin.http.beans.ResponseResult
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
object WanAndroidModelManager {

    val service: WanAndroidService = RetrofitUtils.getService(WanAndroidService::class.java)
    val jobMap: HashMap<WanAndroidModel, ArrayList<Job>> = hashMapOf()

    fun <T> handleResponse(response: Response<ResponseResult<T>>): ResponseResult<T> {
        return if (response.isSuccessful) {
            if (response.body() == null) {
                ResponseResult(ResponseResult.CODE_SUCCESS, "net request success but body is empty")
            } else {
                response.body()!!
            }
        } else {
            ResponseResult(ResponseResult.CODE_HTTP_CODE_ERROR, "error code is ${response.code()}, message is ${response.message()}")
        }
    }

    fun <T> tryCatchAndResult(catchBlock: (Throwable) -> ResponseResult<T> = {
        val errorMsg = when (it) {
            is HttpException -> " 服务器异常"
            is ConnectException -> " 连接错误"
            is JsonParseException -> " 解析异常"
            is UnknownHostException -> " 无网络连接"
            is SocketTimeoutException -> " 链接超时"
            is SocketException,
            is EOFException -> " 链接关闭"
            is IllegalArgumentException -> " 参数错误"
            is SSLException -> " 证书错误"
            else -> "未知错误"
        }
        ResponseResult(ResponseResult.CODE_EXCEPTION, errorMsg)
    }, tryBlock: () -> ResponseResult<T>): ResponseResult<T> {
        return try {
            tryBlock()
        } catch (_: CancellationException) {
            ResponseResult(ResponseResult.CODE_CANCEL, "")
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            catchBlock(throwable)
        } finally {

        }
    }

}