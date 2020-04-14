package com.bee.wanandroidkotlin.utils

import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.base.BaseFragment
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.common.CollectViewModel
import com.bee.wanandroidkotlin.ui.common.activity.LoginActivity
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter

/**
 *
 *  文章收藏扩展类
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */

private const val REQUEST_CODE_LOGIN=10
private fun ArticleListAdapter.getCommonCollectClickListener(model: CollectViewModel, loginBlock: () -> Boolean): (item: ArticleListResponseData, position: Int) -> Unit {
    return fun(item: ArticleListResponseData, position: Int) {
        model.apply {
            if (loginBlock()) {
                return
            }
            if (item.collect) {
                model.cancelCollect(item, successBlock = {
                    item.collect = !item.collect
                    notifyItemChanged(position)
                    ToastAlone.showToast("取消收藏成功")
                }, errorBlock = {
                    ToastAlone.showToast("取消收藏失败")
                })
            } else {
                model.addCollect(item, successBlock = {
                    item.collect = !item.collect
                    notifyItemChanged(position)
                    ToastAlone.showToast("收藏成功")
                }, errorBlock = {
                    ToastAlone.showToast("收藏失败")
                })
            }
        }

    }
}

fun ArticleListAdapter.setCommonCollectClickListener(baseFragment: BaseFragment) {
    val mCollectViewModel = ViewModelProvider(baseFragment).get(CollectViewModel::class.java)
    baseFragment.observeLoadData(mCollectViewModel.loadingData)
    setCollectClickListener(getCommonCollectClickListener(mCollectViewModel) {
        val isLogin by Preference(Constants.SP.SP_LOGIN,false)
        if (isLogin){
            ToastAlone.showToast("请先登录...")
            LoginActivity.startForResult(baseFragment,REQUEST_CODE_LOGIN)
        }
        isLogin
    })
}

fun ArticleListAdapter.setCommonCollectClickListener(baseActivity: BaseActivity) {
    val mCollectViewModel = ViewModelProvider(baseActivity).get(CollectViewModel::class.java)
    baseActivity.observeLoadData(mCollectViewModel.loadingData)
    setCollectClickListener(getCommonCollectClickListener(mCollectViewModel){
        val isLogin by Preference(Constants.SP.SP_LOGIN,false)
        if (isLogin){
            ToastAlone.showToast("请先登录...")
            LoginActivity.startForResult(baseActivity,REQUEST_CODE_LOGIN)
        }
        isLogin
    })
}