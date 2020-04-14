package com.bee.wanandroidkotlin.ui.common

import android.app.Application
import com.bee.wanandroidkotlin.base.BaseAppViewModel
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
class CollectViewModel(application: Application) : BaseAppViewModel(application) {

    /**
     * 添加收藏
     */
    fun addCollect(responseData: ArticleListResponseData,
                   successBlock: () -> Unit = {},
                   errorBlock: () -> Unit = {}) {
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.collect(responseData.id)
            responseResult.handlerResult(errorBlock = {
                ToastAlone.showToast("收藏失败")
                errorBlock()
            }) {
                successBlock()
            }
            loadingData.postValue(false)
        }
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