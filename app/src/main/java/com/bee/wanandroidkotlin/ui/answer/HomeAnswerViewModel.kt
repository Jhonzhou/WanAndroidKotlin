package com.bee.wanandroidkotlin.ui.answer

import android.app.Application
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/10
 * @Description:
 */
class HomeAnswerViewModel (application: Application)
    :BaseRefreshAndListViewModel<ArticleListResponseData>(application){
    override suspend fun getDataFromRepository(preResultList: ArrayList<ArticleListResponseData>): Boolean {
        var result = true
        val answerListCall =
                httpModel.getAnswerList(currentPage)
        answerListCall.handlerResult(errorBlock = {
            if (preResultList.isEmpty()) {
                showErrorPageData.postValue(ErrorState.NET_ERROR)
                result = false
            }
        }) {
            it.data?.apply {
                currentPage = curPage + 1
                datas?.let {
                    preResultList.addAll(datas)
                }
            }
            result = true
        }
        return result
    }

}