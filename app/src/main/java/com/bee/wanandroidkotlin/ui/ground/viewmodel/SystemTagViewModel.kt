package com.bee.wanandroidkotlin.ui.ground.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.http.beans.TagResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */

class SystemTagViewModel(application: Application) : BaseAppViewModel(application) {
    val mSystemTagList: MutableLiveData<List<TagResponseBean>> by lazy {
        MutableLiveData<List<TagResponseBean>>()
    }


    fun getTreeTabList() {
        launchMain {
            loadingData.postValue(true)
            val projectTabListCall = httpModel.getTreeTagList()
            projectTabListCall.handlerResult(errorBlock = {
                if (mSystemTagList.value?.isEmpty() == true) {
                    showErrorPageData.postValue(ErrorState.NET_ERROR)
                }
            }) {

                if (it.data != null) {
                    mSystemTagList.postValue(it.data)
                } else {
                    showErrorPageData.postValue(ErrorState.NO_DATA)
                }
            }
            loadingData.postValue(false)
        }
    }

}