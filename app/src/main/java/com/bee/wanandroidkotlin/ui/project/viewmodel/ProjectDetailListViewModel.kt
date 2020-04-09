package com.bee.wanandroidkotlin.ui.project.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.TagResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class ProjectDetailListViewModel(application: Application) : BaseAppViewModel(application) {
    private var currentPage = 0
    var isLoaded = false
    var initData: TagResponseBean? = null
    val detailListLiveData: MutableLiveData<ArrayList<ArticleListResponseData>> by lazy {
        MutableLiveData<ArrayList<ArticleListResponseData>>()
    }

    fun initData(responseBean: TagResponseBean?) {
        initData = responseBean
    }

    fun loadMoreDetailList() {
        initData ?: return
        val resultList = arrayListOf<ArticleListResponseData>()
        if (currentPage != 0) {
            detailListLiveData.value?.let {
                resultList.addAll(it)
            }
        }
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.getProjectDetailList(currentPage, initData!!.id)
            responseResult.handlerResult(errorBlock = {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
            }) {
                if (it.data == null) {
                    showErrorPageData.postValue(ErrorState.NO_DATA)
                    return@handlerResult
                }
                it.data!!.datas?.apply {
                    resultList.addAll(this)
                }
                currentPage = it.data!!.curPage + 1
                if (resultList.isEmpty()) {
                    showErrorPageData.postValue(ErrorState.NO_DATA)
                } else {
                    detailListLiveData.postValue(resultList)
                }
            }
            loadingData.postValue(false)
        }
    }

    fun getDetailList() {
        currentPage = 0

        isLoaded = true
        loadMoreDetailList()
    }
}