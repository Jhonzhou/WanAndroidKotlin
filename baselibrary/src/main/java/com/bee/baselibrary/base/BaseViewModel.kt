package com.bee.baselibrary.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/26
 * @Description:
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val loadingType: MutableLiveData<Boolean> = MutableLiveData()

}