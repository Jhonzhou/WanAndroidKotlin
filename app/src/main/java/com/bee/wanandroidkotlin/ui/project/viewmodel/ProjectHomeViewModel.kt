package com.bee.wanandroidkotlin.ui.project.viewmodel

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.utils.Preference
import com.bee.baselibrary.utils.launchMain
import com.bee.baselibrary.utils.tryCatch
import com.bee.wanandroidkotlin.base.BaseAppViewModel
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/7
 * @Description:
 */
class ProjectHomeViewModel(application: Application) : BaseAppViewModel(application) {
    private var currentPage: Int = 0
    private var localTabListString: String? by Preference(Constants.SP.SP_PROJECT_TAB_LIST, "")
    val mProjectTagList: MutableLiveData<List<TagResponseBean>> by lazy {
        MutableLiveData<List<TagResponseBean>>()
    }


    fun getProjectTabListFromLocal() {
        if (!TextUtils.isEmpty(localTabListString)) {
            tryCatch {
                val spTabList = Gson()
                        .fromJson<List<TagResponseBean>>(
                                localTabListString,
                                object : TypeToken<List<TagResponseBean>>() {}.type)
                mProjectTagList.postValue(spTabList)
            }
        }
    }

    fun getProjectTabList() {
        launchMain {
            loadingData.postValue(true)
            val projectTabListCall = httpModel.getProjectTabList()
            projectTabListCall.handlerResult(errorBlock = {
                if (mProjectTagList.value?.isEmpty() == true) {
                    showErrorPageData.postValue(ErrorState.NET_ERROR)
                }
            }) {
                mProjectTagList.postValue(it.data)
                if (it.data != null) {
                    localTabListString = Gson().toJson(it.data)
                }
            }
            loadingData.postValue(false)
        }
    }

}