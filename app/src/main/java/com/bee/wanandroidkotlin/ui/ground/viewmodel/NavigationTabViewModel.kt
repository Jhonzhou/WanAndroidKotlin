package com.bee.wanandroidkotlin.ui.ground.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.http.beans.NavigationResponseBean
import com.bee.wanandroidkotlin.utils.launchMain

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class NavigationTabViewModel(application: Application) : BaseAppViewModel(application) {
    val mNavigationTabList: MutableLiveData<List<NavigationResponseBean>> by lazy {
        MutableLiveData<List<NavigationResponseBean>>()
    }

    fun getNavigationTabList() {
        launchMain {
            loadingData.postValue(true)
            val projectTabListCall = httpModel.getNavigationList()
            projectTabListCall.handlerResult {

                if (it.data != null) {
                    mNavigationTabList.postValue(it.data)
                } else {
                    showErrorPageData.postValue(ErrorState.NO_DATA)
                }
            }
            loadingData.postValue(false)
        }
    }

}