package com.bee.wanandroidkotlin.ui.me

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.me.viewmodel.MyCollectionDetailListViewModel

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
        mAdapter.setCollectClickListener { item, position ->
        }
    }
    override fun initData(arguments: Bundle?) {
        initOrPullRefreshData()
    }

}