package com.bee.wanandroidkotlin.utils

import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.common.CollectViewModel
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter

/**
 *
 *  文章收藏扩展类
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */


private fun ArticleListAdapter.getCommonCollectClickListener(model: CollectViewModel): (item: ArticleListResponseData, position: Int) -> Unit {
    return fun(item: ArticleListResponseData, position: Int) {
        model.apply {
            if (item.collect) {
                model.cancelCollect(item, successBlock = {
                    item.collect = !item.collect
                    notifyItemChanged(position)
                    ToastAlone.showToast("取消收藏成功")
                }, errorBlock = {
                    ToastAlone.showToast("取消收藏失败")
                })
            } else {
                model.addCollcet(item, successBlock = {
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

fun ArticleListAdapter.setCommonCollcetClickListener(baseFragment: BaseFragment) {
    val mCollectViewModel = ViewModelProvider(baseFragment).get(CollectViewModel::class.java)
    baseFragment.observeLoadData(mCollectViewModel.loadingData)
    setCollectClickListener(getCommonCollectClickListener(mCollectViewModel))
}

fun ArticleListAdapter.setCommonCollcetClickListener(baseActivity: BaseActivity) {
    val mCollectViewModel = ViewModelProvider(baseActivity).get(CollectViewModel::class.java)
    baseActivity.observeLoadData(mCollectViewModel.loadingData)
    setCollectClickListener(getCommonCollectClickListener(mCollectViewModel))
}