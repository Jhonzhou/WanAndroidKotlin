package com.bee.wanandroidkotlin.http

import com.bee.baselibrary.base.BaseDataModel
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.handleResponse
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.jobMap
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.service
import com.bee.wanandroidkotlin.http.WanAndroidModelManager.tryCatchAndResult
import com.bee.wanandroidkotlin.http.beans.*
import com.bee.wanandroidkotlin.utils.tryCatch
import kotlinx.coroutines.*

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

    suspend fun logout(): ResponseResult<Any> {
        return withHttpContext {
            val loginCall = service.logout()
            val response = loginCall.execute()
            handleResponse(response)
        }
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

    suspend fun getHomePageList(page: Int): ResponseResult<PageListResponse> {
        return withHttpContext {
            val homePageListCall = service.getHomePageList(page)
            val response = homePageListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun search(page: Int, key: String): ResponseResult<PageListResponse> {
        return withHttpContext {
            val homePageListCall = service.search(page, key)
            val response = homePageListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getProjectDetailList(page: Int, cId: Int): ResponseResult<PageListResponse> {
        return withHttpContext {
            val mProjectTabListCall = service.getProjectDetailList(page, cId)
            val response = mProjectTabListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getProjectTabList(): ResponseResult<List<TagResponseBean>> {
        return withHttpContext {
            val mProjectTabListCall = service.getProjectTabList()
            val response = mProjectTabListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getTreeDetailList(page: Int, cId: Int): ResponseResult<PageListResponse> {
        return withHttpContext {
            val mListCall = service.getTreeDetailList(page, cId)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getTreeTagList(): ResponseResult<List<TagResponseBean>> {
        return withHttpContext {
            val mListCall = service.getTreeTagList()
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getNavigationList(): ResponseResult<List<NavigationResponseBean>> {
        return withHttpContext {
            val mListCall = service.getNavigationList()
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getTencentList(): ResponseResult<List<TagResponseBean>> {
        return withHttpContext {
            val mListCall = service.getTencentList()
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getTencentDetailList(page: Int, cid: Int): ResponseResult<PageListResponse> {
        return withHttpContext {
            val mListCall = service.getTencentDetailList(page, cid)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getAnswerList(page: Int): ResponseResult<PageListResponse> {
        return withHttpContext {
            val mListCall = service.getAnswerList(page)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun getIntegral(): ResponseResult<IntegralResponseData> {
        return withHttpContext {
            val mListCall = service.getIntegral()
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun collect(id: Int): ResponseResult<Any> {
        return withHttpContext {
            val mListCall = service.collect(id)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun unCollect(id: Int): ResponseResult<Any> {
        return withHttpContext {
            val mListCall = service.unCollect(id)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

    suspend fun unCollectFromDetail(id: Int): ResponseResult<Any> {
        return withHttpContext {
            val mListCall = service.unCollectFromDetail(id)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }
    suspend fun getCollectList(page: Int): ResponseResult<PageListResponse> {
        return withHttpContext {
            val mListCall = service.getCollectList(page)
            val response = mListCall.execute()
            handleResponse(response)
        }
    }

}

