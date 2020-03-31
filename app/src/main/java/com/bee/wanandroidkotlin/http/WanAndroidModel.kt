package com.bee.wanandroidkotlin.http

import com.bee.baselibrary.base.BaseDataModel
import com.bee.baselibrary.utils.tryCatch
import com.bee.wanandroidkotlin.http.beans.LoginResponseBean
import com.bee.wanandroidkotlin.http.beans.ResponseResult
import com.google.gson.JsonParseException
import kotlinx.coroutines.*
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
 * @date:  2020/3/24
 * @Description:
 */
class WanAndroidModel : BaseDataModel() {
    override fun onDestroy() {
        val jobList = jobMap[this]
        jobList?.apply {
            forEach {
                tryCatch {
                    if (it.isActive) {
                        it.cancel()
                    }
                }
            }
        }
        jobMap.remove(this)

    }

    companion object {
        private val service: WanAndroidService = RetrofitUtils.getService(WanAndroidService::class.java)
        private val jobMap: HashMap<WanAndroidModel, ArrayList<Job>> = hashMapOf()

        private fun <T> handleResponse(response: Response<ResponseResult<T>>): ResponseResult<T> {
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

        private fun <T> tryCatchAndResult(catchBlock: (Throwable) -> ResponseResult<T> = {
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
                catchBlock(throwable)
            } finally {

            }
        }

    }

    private fun addToCancelMap(job: Job) {
        var list = jobMap[this]
        list = list ?: arrayListOf()
        list.add(job)
        jobMap[this] = list
        jobMap.remove(this)
    }

    private suspend fun <T> withHttpContext(block: () -> ResponseResult<T>): ResponseResult<T> {
        val job = GlobalScope.async {
            withContext(Dispatchers.IO) {
                tryCatchAndResult {
                    block()
                }

            }
        }
        addToCancelMap(job)
        job.await()
        return job.getCompleted()
    }

    suspend fun login(username: String, password: String): ResponseResult<LoginResponseBean> {
        return withHttpContext {
            val loginCall = service.login(username, password)
            val response = loginCall.execute()
            handleResponse(response)
        }
    }

    suspend fun register(username: String, password: String, rePassword: String): ResponseResult<LoginResponseBean> {
        return withHttpContext {
            val registerCall = service.register(username, password, rePassword)
            val response = registerCall.execute()
            handleResponse(response)
        }
    }

}

