package com.bee.wanandroidkotlin.utils

import android.view.View
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.R
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.base.BaseFragment

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */

fun BaseActivity.observeErrorData(errorState: MutableLiveData<ErrorState>,
                                  noDataReTryBlock: () -> Unit = {},
                                  noNetReTryBlock: () -> Unit = {}) {
    errorState.observe(this, Observer {
        when (it) {
            ErrorState.NET_ERROR -> {
                showErrorPage(it) {
                    showCorrectPage()
                    noNetReTryBlock()
                }
            }
            ErrorState.NO_DATA -> {
                showErrorPage(it)
                noDataReTryBlock()
            }
            else -> {
            }
        }
    })
}

fun BaseFragment.observeErrorData(errorState: MutableLiveData<ErrorState>,
                                  noDataReTryBlock: () -> Unit = {},
                                  noNetReTryBlock: () -> Unit = {}) {
    errorState.observe(this, Observer {
        when (it) {
            ErrorState.NET_ERROR -> {
                showErrorPage(it) {
                    showCorrectPage()
                    noNetReTryBlock()
                }
            }
            ErrorState.NO_DATA -> {
                showErrorPage(it)
                noDataReTryBlock()
            }
            else -> {
            }
        }
    })
}

fun BaseFragment.observeLoadData(loadingData: MutableLiveData<Boolean>,
                                 showBlock: () -> Unit = {},
                                 hideBlock: () -> Unit = {}) {
    loadingData.observe(this, Observer {
        if (it) {
            showLoadingDialog()
            showBlock()
        } else {
            hideLoadingDialog()
            hideBlock()
        }
    })
}

fun BaseActivity.observeLoadData(loadingData: MutableLiveData<Boolean>,
                                 showBlock: () -> Unit = {},
                                 hideBlock: () -> Unit = {}) {
    loadingData.observe(this, Observer {
        if (it) {
            showLoadingDialog()
            showBlock()
        } else {
            hideLoadingDialog()
            hideBlock()
        }
    })
}


fun BaseFragment.showCorrectPage() {
    flBaseContainer.visibility = View.VISIBLE
    clErrorLayout.visibility = View.GONE
}

fun BaseFragment.showErrorPage(state: ErrorState, textClickListener: () -> Unit = {}) {
    val errorContent: String
    @DrawableRes val errorImage: Int
    when (state) {
        ErrorState.NET_ERROR -> {
            errorContent = "网络异常"
            errorImage = R.drawable.ic_net_error
        }
        ErrorState.NO_DATA -> {
            errorContent = "无数据"
            errorImage = R.drawable.ic_no_data
        }
        else -> {
            return
        }
    }
    flBaseContainer.visibility = View.GONE
    clErrorLayout.visibility = View.VISIBLE
    tvErrorContent.text = errorContent
    ivErrorContent.setImageResource(errorImage)
    tvErrorContent.setOnClickListener {
        textClickListener()
    }
}

fun BaseActivity.showCorrectPage() {
    flBaseContainer.visibility = View.VISIBLE
    clErrorLayout.visibility = View.GONE
}

fun BaseActivity.showErrorPage(state: ErrorState, textClickListener: () -> Unit = {}) {
    val errorContent: String
    @DrawableRes val errorImage: Int
    when (state) {
        ErrorState.NET_ERROR -> {
            errorContent = "网络异常"
            errorImage = R.drawable.ic_net_error
        }
        ErrorState.NO_DATA -> {
            errorContent = "无数据"
            errorImage = R.drawable.ic_no_data
        }
        else -> {
            return
        }
    }
    clErrorLayout.visibility = View.GONE
    clErrorLayout.visibility = View.VISIBLE
    tvErrorContent.text = errorContent
    ivErrorContent.setImageResource(errorImage)
    tvErrorContent.setOnClickListener {
        textClickListener()
    }
}