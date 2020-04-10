package com.bee.wanandroidkotlin.ui.ground.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.utils.launchMain

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class TencentTagListViewModel(application: Application) : BaseAppViewModel(application) {
    val mTencentList: MutableLiveData<List<TagResponseBean>> by lazy {
        MutableLiveData<List<TagResponseBean>>()
    }

    fun getTencentList() {
        launchMain {
            loadingData.postValue(true)
            val projectTabListCall = httpModel.getTencentList()
            projectTabListCall.handlerResult(errorBlock = {
                if (mTencentList.value?.isEmpty() != false) {
                    showErrorPageData.postValue(ErrorState.NET_ERROR)
                }
            }) {

                if (it.data != null) {
                    mTencentList.postValue(it.data)
                } else {
                    showErrorPageData.postValue(ErrorState.NO_DATA)
                }
            }
            loadingData.postValue(false)
        }
    }
}