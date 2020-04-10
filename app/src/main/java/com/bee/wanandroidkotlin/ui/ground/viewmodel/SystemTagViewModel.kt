package com.bee.wanandroidkotlin.ui.ground.viewmodel

import android.app.Application
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListViewModel
import com.bee.wanandroidkotlin.http.beans.TagResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */

class SystemTagViewModel(application: Application) : BaseRefreshAndListViewModel<TagResponseBean>(application) {
    override suspend fun getDataFromRepository(preResultList: ArrayList<TagResponseBean>): Boolean {
        var result = true
        val projectTabListCall = httpModel.getTreeTagList()
        projectTabListCall.handlerResult(errorBlock = {
            if (preResultList.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
                result = false
            }
        }) {
            it.data?.apply {
                preResultList.addAll(this)
            }
        }

        return result
    }

}