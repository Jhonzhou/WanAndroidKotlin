package com.bee.wanandroidkotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.HomeBannerResponse
import com.bee.wanandroidkotlin.utils.ToastAlone

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
class HomeFirstViewModel(application: Application) : BaseAppViewModel(application) {
    private var currentPage = 0
    val bannerData: MutableLiveData<List<HomeBannerResponse>> by lazy {
        MutableLiveData<List<HomeBannerResponse>>()
    }
    val homePageListData: MutableLiveData<ArrayList<ArticleListResponseData>> by lazy {
        MutableLiveData<ArrayList<ArticleListResponseData>>()
    }

    fun getHomeBanner() {
        launchMain {
            val homeBannerResult = httpModel.getHomeBanner()
            homeBannerResult.handlerResult(errorBlock = {
                bannerData.postValue(null)
            }) {
                bannerData.postValue(it.data)
            }
        }
    }

    fun reLoadHomePageList() {
        launchMain {
            loadingData.postValue(true)
            currentPage = 0
            val topList = getTopList()
            val homePageList = getHomePageList()

            loadingData.postValue(false)
            if (homePageList == null) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
                return@launchMain
            } else {
                topList.addAll(homePageList)
            }
            if (topList.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NO_DATA)
            } else {
                homePageListData.postValue(topList)
            }

        }
    }

    private suspend fun getTopList(): ArrayList<ArticleListResponseData> {
        val topList: ArrayList<ArticleListResponseData> = arrayListOf()
        val topListResult = httpModel.getTopList()

        topListResult.handlerResult {
            it.data?.apply {
                topList.addAll(this)
            }
        }
        return topList
    }

    fun loadMoreHomePageList() {
        launchMain {
            loadingData.postValue(true)
            homePageListData.postValue(getHomePageList())
            loadingData.postValue(false)
        }
    }

    /**
     * 仅当首页网络请求失败时返回null
     */
    private suspend fun getHomePageList(): ArrayList<ArticleListResponseData>? {
        var resultList: ArrayList<ArticleListResponseData>? = arrayListOf()
        if (currentPage != 0) {
            homePageListData.value?.let {
                resultList!!.addAll(it)
            }
        }
        val homePageList = httpModel.getHomePageList(currentPage)
        homePageList.handlerResult(errorBlock = {
            ToastAlone.showToast(it.errorMsg)
            if (resultList!!.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
                resultList = null
            }
        }) {
            it.data?.apply {
                currentPage = curPage
                if (datas != null) {
                    resultList!!.addAll(datas)
                }
            }
        }
        return resultList
    }

}
