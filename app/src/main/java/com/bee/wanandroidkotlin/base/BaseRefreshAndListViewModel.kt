package com.bee.wanandroidkotlin.base

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.utils.launchMain

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/10
 * @Description:
 */
abstract class BaseRefreshAndListViewModel<D>(application: Application) : BaseAppViewModel(application) {
    protected var currentPage = 0
    val mListLiveData: MutableLiveData<List<D>> by lazy {
        MutableLiveData<List<D>>()
    }

    fun loadMoreDataList() {
        launchMain {
            loadingData.postValue(true)
            var preResultList: ArrayList<D> = arrayListOf()
            if (currentPage != 0) {
                mListLiveData.value?.apply {
                    preResultList.addAll(this)
                }
            }
            if (getDataFromRepository(preResultList)) {
                handlerResult(preResultList)
            }
            loadingData.postValue(false)
        }
    }

    private fun handlerResult(resultList: List<D>) {
        if (resultList.isNotEmpty()) {
            mListLiveData.postValue(resultList)
        } else {
            showErrorPageData.postValue(ErrorState.NO_DATA)
        }
    }

    abstract suspend fun getDataFromRepository(preResultList: ArrayList<D>):Boolean

    fun initOrPullRefreshDataList() {
        currentPage = 0
        loadMoreDataList()
    }
}