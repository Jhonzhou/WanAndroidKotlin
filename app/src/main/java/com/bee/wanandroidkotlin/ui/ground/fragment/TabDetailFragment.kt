package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.ground.viewmodel.SystemTabDetailViewModel
import com.bee.wanandroidkotlin.utils.ToastAlone
import com.bee.wanandroidkotlin.utils.setCommonCollcetClickListener

/**
 *
 *根据TagResponseBean 获取对应类别activity
 * @author: JhonZhou
 * @date:  2020/4/10
 * @Description:
 */
class TabDetailFragment
    : BaseRefreshAndListFragment<ArticleListResponseData, ArticleListAdapter, SystemTabDetailViewModel>() {
    override val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }
    override val mViewModel: SystemTabDetailViewModel by lazy {
        ViewModelProvider(this).get(SystemTabDetailViewModel::class.java)
    }


    override fun initData(arguments: Bundle?) {
        val responseBean = arguments?.getSerializable(Constants.KEY_DATA) as TagResponseBean?
        toolBarBuilder.setTitle(responseBean?.name ?: "体系")
        mViewModel.initData(responseBean)
        mViewModel.initOrPullRefreshDataList()
    }

    override fun initListener() {
        super.initListener()
        mAdapter.setCommonCollcetClickListener(this)
    }

    override fun onItemClick(item: ArticleListResponseData, position: Int) {
        super.onItemClick(item, position)
        ToastAlone.showToast(item.title)
    }
}