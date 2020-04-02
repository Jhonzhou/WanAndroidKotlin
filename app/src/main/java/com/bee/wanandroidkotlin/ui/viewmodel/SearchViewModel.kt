package com.bee.wanandroidkotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.base.BaseViewModel
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/2
 * @Description:
 */
class SearchViewModel(application: Application) : BaseViewModel(application) {
    val searchResultData: MutableLiveData<ArrayList<ArticleListResponseData>> by lazy {
        MutableLiveData<ArrayList<ArticleListResponseData>>()
    }
}