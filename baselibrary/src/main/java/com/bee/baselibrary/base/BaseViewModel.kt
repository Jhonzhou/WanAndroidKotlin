package com.bee.baselibrary.base

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

    override fun onCleared() {
        super.onCleared()
        httpModel?.onDestroy()
    }
}