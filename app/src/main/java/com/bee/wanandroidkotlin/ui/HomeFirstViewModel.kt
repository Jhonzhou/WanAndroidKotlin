package com.bee.wanandroidkotlin.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.base.BaseViewModel
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.http.WanAndroidModel
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
    val bannerData: MutableLiveData<ResponseResult<List<HomeBannerResponse>>> by lazy {
        MutableLiveData<ResponseResult<List<HomeBannerResponse>>>()
    }
    private val httpModel: WanAndroidModel by lazy {
        WanAndroidModel()
    }

    fun getHomeBanner() {
        launchMain {
            bannerData.postValue(httpModel.getHomeBanner())
        }
    }

    override fun onCleared() {
        super.onCleared()
        httpModel.onDestroy()
    }

}
