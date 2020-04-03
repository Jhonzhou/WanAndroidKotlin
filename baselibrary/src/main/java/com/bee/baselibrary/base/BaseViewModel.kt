package com.bee.baselibrary.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bee.baselibrary.ErrorState

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/26
 * @Description:
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val loadingData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val showErrorPageData: MutableLiveData<ErrorState>by lazy {
        MutableLiveData<ErrorState>()
    }

}