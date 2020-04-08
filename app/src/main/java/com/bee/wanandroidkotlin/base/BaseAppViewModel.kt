package com.bee.wanandroidkotlin.base

import android.app.Application
import com.bee.baselibrary.base.BaseViewModel
import com.bee.wanandroidkotlin.http.WanAndroidModel

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/7
 * @Description:
 */
open class BaseAppViewModel(application: Application) :BaseViewModel(application){
    protected val httpModel: WanAndroidModel by lazy {
        WanAndroidModel()
    }

    override fun onCleared() {
        super.onCleared()
        httpModel.onDestroy()
    }
}