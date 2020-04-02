package com.bee.wanandroidkotlin.ui.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.base.BaseViewModel
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.http.WanAndroidModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/2
 * @Description:
 */
class SearchViewModel(application: Application) : BaseViewModel(application) {
    private var currentPage = 0
    private val httpModel: WanAndroidModel by lazy {
        WanAndroidModel()
    }
    val searchResultData: MutableLiveData<ArrayList<ArticleListResponseData>> by lazy {
        MutableLiveData<ArrayList<ArticleListResponseData>>()
    }

    fun loadMoreSerachResult(keyWord: String?) {
        if (TextUtils.isEmpty(keyWord)) {
            searchResultData.postValue(null)
            return
        }
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.search(currentPage, keyWord!!)
            responseResult.handlerResult {
                it.data?.apply {
                    currentPage = curPage
                    val preResultList = arrayListOf<ArticleListResponseData>()
                    datas ?: return@apply
                    datas.addAll(0, searchResultData.value!!)
                    searchResultData.postValue(datas)


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
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.search(currentPage, keyWord!!)
            responseResult.handlerResult {
                it.data?.apply {
                    currentPage = curPage
                    searchResultData.postValue(datas)
                }
            }
            loadingData.postValue(false)
        }

    }
}