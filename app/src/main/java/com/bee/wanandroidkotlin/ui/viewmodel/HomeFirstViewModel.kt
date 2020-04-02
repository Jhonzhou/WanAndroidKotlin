package com.bee.wanandroidkotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.base.BaseViewModel
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.http.WanAndroidModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.HomeBannerResponse
import com.bee.wanandroidkotlin.http.beans.ResponseResult

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
class HomeFirstViewModel(application: Application) : BaseViewModel(application) {
    private var currentPage = 0
    val bannerData: MutableLiveData<ResponseResult<List<HomeBannerResponse>>> by lazy {
        MutableLiveData<ResponseResult<List<HomeBannerResponse>>>()
    }
    val homePageListData: MutableLiveData<ArrayList<ArticleListResponseData>> by lazy {
        MutableLiveData<ArrayList<ArticleListResponseData>>()
    }
    private val httpModel: WanAndroidModel by lazy {
        WanAndroidModel()
    }

    fun getHomeBanner() {
        launchMain {
            bannerData.postValue(httpModel.getHomeBanner())
        }
    }

    fun reLoadHomePageList() {
        launchMain {
            loadingData.postValue(true)
            currentPage = 0
            val topList: ArrayList<ArticleListResponseData> = arrayListOf()
            val topListResult = httpModel.getTopList()
            topListResult.handlerResult {
                it.data?.apply {
                    topList.addAll(this)
                }
            }
            topList.addAll(getHomePageList())
            homePageListData.postValue(topList)
            loadingData.postValue(false)
        }
    }

    fun loadHomePageList() {
        launchMain {
            loadingData.postValue(true)
            homePageListData.postValue(getHomePageList())
            loadingData.postValue(false)
        }
    }

    private suspend fun getHomePageList(): ArrayList<ArticleListResponseData> {
        val resultList: ArrayList<ArticleListResponseData> = arrayListOf()
        val homePageList = httpModel.getHomePageList(currentPage)
        homePageList.handlerResult {
            it.data?.apply {
                currentPage = curPage
                if (datas != null) {
                    resultList.addAll(datas)
                }
            }
        }
        return resultList
    }

    override fun onCleared() {
        super.onCleared()
        httpModel.onDestroy()
    }

}
