package com.bee.wanandroidkotlin.ui.me

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.view.dialog.HintDialogOrPopItemBean
import com.bee.baselibrary.view.dialog.ui.MiddleListDialogFragment
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.common.activity.DetailContentWebActivity
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.me.viewmodel.MyCollectionDetailListViewModel
import com.bee.wanandroidkotlin.utils.ToastAlone

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */
class MyCollectionDetailListFragment :
        BaseRefreshAndListFragment<ArticleListResponseData, ArticleListAdapter, MyCollectionDetailListViewModel>() {
    override val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }
    override val mViewModel: MyCollectionDetailListViewModel by lazy {
        ViewModelProvider(this).get(MyCollectionDetailListViewModel::class.java)
    }

    override fun initListener() {
        super.initListener()
        toolBarBuilder.setTitle("我的收藏")
        mAdapter.setOnItemClickListener(object : BaseRvAdapter.OnItemClickListener<ArticleListResponseData>{
            override fun onItemClick(baseViewHolder: BaseViewHolder, position: Int, item: ArticleListResponseData) {
                DetailContentWebActivity.startFragment(this@MyCollectionDetailListFragment, item.link, item.title)
            }

        })
        mAdapter.setOnItemLongClickListener(object : BaseRvAdapter.OnItemLongClickListener<ArticleListResponseData> {
            override fun onItemLongClick(baseViewHolder: BaseViewHolder, position: Int, item: ArticleListResponseData): Boolean {
                MiddleListDialogFragment.Builder().addItem(HintDialogOrPopItemBean("删除") {
                    mViewModel.cancelCollect(item, successBlock = {
                        ToastAlone.showToast("取消收藏成功")
                        mAdapter.removePosition(position)
                    }, errorBlock = {
                        ToastAlone.showToast("取消收藏失败")
                    })
                }).create()
                        .show(childFragmentManager, "")
                return true
            }

        })
    }

    override fun initData(arguments: Bundle?) {
        initOrPullRefreshData()
    }

}