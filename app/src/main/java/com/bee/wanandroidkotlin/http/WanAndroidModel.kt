package com.bee.wanandroidkotlin.http

import com.bee.baselibrary.base.BaseDataModel
import com.bee.wanandroidkotlin.http.beans.LoginResponseBean
import com.bee.wanandroidkotlin.http.beans.ResponseResult
import com.bee.wanandroidkotlin.utils.tryCatch
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

        private fun <T> handleException(throwable: Throwable, clazz: Class<T>? = null): ResponseResult<T> {
            val errorMsg = if (throwable.message == null) {
                "网络请求失败,请稍后再试"
            } else {
                throwable.message.toString()
            }
            return ResponseResult(ResponseResult.CODE_EXCEPTION, errorMsg)
        }

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
    }

    suspend fun login(username: String, password: String): ResponseResult<LoginResponseBean> {
        val job = GlobalScope.async {
            withContext(Dispatchers.IO) {
                tryCatchAndResult {
                    val loginCall = service.login(username, password)
                    val response = loginCall.execute()
                    handleResponse(response)
                }
            }
        }
        addToCancelMap(job)
        job.await()
        return job.getCompleted()
    }

    suspend fun register(username: String, password: String, rePassword: String): ResponseResult<LoginResponseBean> {
        val job = GlobalScope.async {
            withContext(Dispatchers.IO) {
                tryCatchAndResult {
                    val registerCall = service.register(username, password, rePassword)
                    val response = registerCall.execute()
                    handleResponse(response)
                }
            }
        }
        addToCancelMap(job)
        job.join()
        return job.getCompleted()
    }

}

