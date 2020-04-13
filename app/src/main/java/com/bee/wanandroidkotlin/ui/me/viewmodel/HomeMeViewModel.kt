package com.bee.wanandroidkotlin.ui.me.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.IntegralResponseData
import com.bee.wanandroidkotlin.utils.launchMain
import com.google.gson.Gson

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
class HomeMeViewModel(application: Application) : BaseAppViewModel(application) {
    private var localIntegralString by Preference(Constants.SP.SP_INTEGRAL, "")
    val mIntegralLiveData: MutableLiveData<IntegralResponseData> by lazy {
        MutableLiveData<IntegralResponseData>()
    }

    fun getIntegralFromLocal() {
        if (!TextUtils.isEmpty(localIntegralString)) {
            val responseData = Gson().fromJson(localIntegralString, IntegralResponseData::class.java)
            mIntegralLiveData.postValue(responseData)
        }
    }

    fun getIntegral() {
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.getIntegral()
            responseResult.handlerResult {
                it.data?.apply {
                    mIntegralLiveData.postValue(this)
                    localIntegralString = Gson().toJson(this)
                }
            }
            loadingData.postValue(false)
        }
    }
}