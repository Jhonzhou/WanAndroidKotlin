package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.ground.viewmodel.TencentDetailListViewModel
import com.bee.wanandroidkotlin.utils.ToastAlone
import com.bee.wanandroidkotlin.utils.setCommonCollectClickListener
import com.bee.wanandroidkotlin.utils.setItemClick

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class TencentDetailListFragment :
        BaseRefreshAndListFragment<ArticleListResponseData, ArticleListAdapter, TencentDetailListViewModel>() {
    override val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }
    override val mViewModel: TencentDetailListViewModel by lazy {
        ViewModelProvider(this).get(TencentDetailListViewModel::class.java)
    }


    override fun initData(arguments: Bundle?) {
        val responseBean = arguments?.getSerializable(Constants.KEY_DATA) as TagResponseBean?
        toolBarBuilder.setTitle(responseBean?.name ?: "公众号")
        mViewModel.initData(responseBean)

        mViewModel.initOrPullRefreshDataList()
    }

    override fun initListener() {
        super.initListener()
        mAdapter.setCommonCollectClickListener(this)
        mAdapter.setItemClick(this)
    }

    override fun onItemClick(item: ArticleListResponseData, position: Int) {
        super.onItemClick(item, position)
        ToastAlone.showToast(item.title)
    }


}