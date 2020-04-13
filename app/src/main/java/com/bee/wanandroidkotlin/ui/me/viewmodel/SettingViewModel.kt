package com.bee.wanandroidkotlin.ui.me.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.utils.launchMain

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
class SettingViewModel(application: Application) : BaseAppViewModel(application) {
    val mLogoutResult: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun logout() {
        launchMain {
            loadingData.postValue(true)
            val responseResult = httpModel.logout()
            responseResult.handlerResult(errorBlock = {
                mLogoutResult.postValue(false)
            }) {
                mLogoutResult.postValue(true)

            }
            loadingData.postValue(false)
        }
    }
}