package com.bee.wanandroidkotlin.ui.home.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.utils.launchMain
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.utils.ToastAlone

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/2
 * @Description:
 */
class SearchViewModel(application: Application) : BaseAppViewModel(application) {
    private var currentPage = 0

    val searchResultData: MutableLiveData<ArrayList<ArticleListResponseData>> by lazy {
        MutableLiveData<ArrayList<ArticleListResponseData>>()
    }

    fun loadMoreSearchResult(keyWord: String?) {
        if (TextUtils.isEmpty(keyWord)) {
            searchResultData.postValue(null)
            return
        }
        val resultList: ArrayList<ArticleListResponseData> = arrayListOf()
        if (currentPage != 0) {
            searchResultData.value?.let {
                resultList.addAll(it)
            }
        }
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.search(currentPage, keyWord!!)

            responseResult.handlerResult(errorBlock = {
                ToastAlone.showToast(it.errorMsg)
                //如果无数据发送请求失败则显示网络错误界面
                if (resultList.isEmpty()) {
                    loadingData.postValue(false)
                    showErrorPageData.postValue(ErrorState.NET_ERROR)
                    return@handlerResult
                }
            }) {
                it.data?.apply {
                    currentPage = curPage
                    datas ?: return@apply
                    resultList.addAll(datas)
                }
                if (resultList.isEmpty()) {
                    showErrorPageData.postValue(ErrorState.NO_DATA)
                } else {
                    searchResultData.postValue(resultList)
                }
            }
            loadingData.postValue(false)
        }
    }

    fun search(keyWord: String?) {
        currentPage = 0
        if (TextUtils.isEmpty(keyWord)) {
            searchResultData.postValue(null)
            return
        }
        searchResultData.postValue(null)
        loadMoreSearchResult(keyWord)

    }
}