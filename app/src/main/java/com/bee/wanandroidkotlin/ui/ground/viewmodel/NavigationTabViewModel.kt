package com.bee.wanandroidkotlin.ui.ground.viewmodel

import android.app.Application
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListViewModel
import com.bee.wanandroidkotlin.http.beans.NavigationResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class NavigationTabViewModel(application: Application) : BaseRefreshAndListViewModel<NavigationResponseBean>(application) {
    override suspend fun getDataFromRepository(preResultList: ArrayList<NavigationResponseBean>): Boolean {
        var result = true
        val projectTabListCall = httpModel.getNavigationList()
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