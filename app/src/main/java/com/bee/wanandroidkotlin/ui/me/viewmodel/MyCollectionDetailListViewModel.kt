package com.bee.wanandroidkotlin.ui.me.viewmodel

import android.app.Application
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.utils.ToastAlone
import com.bee.wanandroidkotlin.utils.launchMain

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */
class MyCollectionDetailListViewModel(application: Application)
    : BaseRefreshAndListViewModel<ArticleListResponseData>(application) {
    override suspend fun getDataFromRepository(preResultList: ArrayList<ArticleListResponseData>): Boolean {
        var result = true
        val answerListCall =
                httpModel.getCollectList(currentPage)
        answerListCall.handlerResult(errorBlock = {
            if (preResultList.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
                result = false
            }
        }) {
            it.data?.apply {
                currentPage = curPage + 1
                datas?.apply {
                    forEach { responseData ->
                        responseData.collect = true
                    }
                    preResultList.addAll(this)
                }
            }
            result = true
        }
        return result
    }

    /**
     * 取消收藏
     */
    fun cancelCollect(responseData: ArticleListResponseData,
                      successBlock: () -> Unit = {},
                      errorBlock: () -> Unit = {}) {
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.unCollect(responseData.id)
            responseResult.handlerResult(errorBlock = {
                ToastAlone.showToast("取消收藏失败")
                errorBlock()
            }) {
                successBlock()
            }
            loadingData.postValue(false)
        }
    }

}