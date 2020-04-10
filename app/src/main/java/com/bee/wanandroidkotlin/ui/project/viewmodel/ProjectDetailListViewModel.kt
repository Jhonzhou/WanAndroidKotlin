package com.bee.wanandroidkotlin.ui.project.viewmodel

import android.app.Application
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.TagResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class ProjectDetailListViewModel(application: Application) : BaseRefreshAndListViewModel<ArticleListResponseData>(application) {
    var isLoaded = false
    var initData: TagResponseBean? = null

    fun initData(responseBean: TagResponseBean?) {
        initData = responseBean
    }

    override suspend fun getDataFromRepository(preResultList: ArrayList<ArticleListResponseData>): Boolean {
        var result = true
        initData ?: return false
        val responseResult = httpModel.getProjectDetailList(currentPage, initData!!.id)
        responseResult.handlerResult(errorBlock = {
            if (preResultList.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
            }
            result = false
        }) {
            it.data?.apply {
                currentPage = curPage + 1
                datas?.let {
                    preResultList.addAll(it)
                }
            }
            if (it.data == null) {
                showErrorPageData.postValue(ErrorState.NO_DATA)
                return@handlerResult
            }
            result = true
        }
        return result
    }

}