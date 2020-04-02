package com.bee.wanandroidkotlin.http

import com.bee.baselibrary.base.BaseDataModel
import com.bee.baselibrary.utils.tryCatch
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.handleResponse
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.jobMap
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.service
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.tryCatchAndResult
import com.bee.wanandroidkotlin.http.beans.*
import kotlinx.coroutines.*

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/24
 * @Description:
 */
class WanAndroidModel : BaseDataModel() {
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

    suspend fun getHomeBanner(): ResponseResult<List<HomeBannerResponse>> {
        return withHttpContext {
            val homeBannerCall = service.getHomeBanner()
            val response = homeBannerCall.execute()
            handleResponse(response)

        }
    }

    suspend fun getTopList(): ResponseResult<ArrayList<ArticleListResponseData>> {
        return withHttpContext {
            val topListCall = service.getTopList()
            val response = topListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getHomePageList(page: Int): ResponseResult<ArticlePageListResponse> {
        return withHttpContext {
            val homePageListCall = service.getHomePageList(page)
            val response = homePageListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun search(page: Int, key: String): ResponseResult<ArticlePageListResponse> {
        return withHttpContext {
            val homePageListCall = service.search(page, key)
            val response = homePageListCall.execute()
            handleResponse(response)
        }
    }


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


}

