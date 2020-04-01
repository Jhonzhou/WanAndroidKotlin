package com.bee.wanandroidkotlin.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.base.BaseViewModel
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.http.WanAndroidModel
import com.bee.wanandroidkotlin.http.beans.HomeBannerResponse
import com.bee.wanandroidkotlin.http.beans.HomePageListResponseData
import com.bee.wanandroidkotlin.http.beans.ResponseResult
import kotlinx.coroutines.delay

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
    val loadingData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val homePageListData: MutableLiveData<ArrayList<HomePageListResponseData>> by lazy {
        MutableLiveData<ArrayList<HomePageListResponseData>>()
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
            val topList: ArrayList<HomePageListResponseData> = arrayListOf()
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

    private suspend fun getHomePageList(): ArrayList<HomePageListResponseData> {
        val resultList: ArrayList<HomePageListResponseData> = arrayListOf()
        delay(5000L)
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
