package com.bee.wanandroidkotlin.ui.ground.viewmodel

import android.app.Application
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.TagResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/10
 * @Description:
 */
class SystemTabDetailViewModel(application: Application)
    : BaseRefreshAndListViewModel<ArticleListResponseData>(application) {
    private var tagResponseBean: TagResponseBean? = null
    fun initData(responseBean: TagResponseBean?) {
        tagResponseBean = responseBean
    }

    override suspend fun getDataFromRepository(preResultList: ArrayList<ArticleListResponseData>): Boolean {
        var result = true
        tagResponseBean ?: return result
        val tencentTabListCall =
                httpModel.getTreeDetailList(currentPage, tagResponseBean!!.id)
        tencentTabListCall.handlerResult(errorBlock = {
            if (preResultList.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
                result = false
            }
        }) {
            it.data?.apply {
                currentPage = curPage + 1
                datas?.let {
                    preResultList.addAll(datas)
                }
            }
            result = true
        }
        return result
    }

}