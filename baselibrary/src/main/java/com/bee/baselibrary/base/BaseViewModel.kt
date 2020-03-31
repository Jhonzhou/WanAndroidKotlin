package com.bee.baselibrary.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/26
 * @Description:
 */
open class BaseViewModel<T : BaseDataModel> : ViewModel() {
    var httpModel: T? = null
    protected val loadingType: MutableLiveData<Boolean> = MutableLiveData()
    override fun onCleared() {
        super.onCleared()
        httpModel?.onDestroy()
    }
    
}