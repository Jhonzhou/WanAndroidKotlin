package com.bee.wanandroidkotlin.ui.project.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.project.viewmodel.ProjectDetailListViewModel
import com.bee.wanandroidkotlin.utils.observeErrorData
import com.bee.wanandroidkotlin.utils.observeLoadData
import com.bee.wanandroidkotlin.utils.setOnLoadMoreListener
import com.bee.wanandroidkotlin.utils.showErrorPage
import kotlinx.android.synthetic.main.common_refresh_and_recycleview.*

/**
 *
 *  项目界面详情列表fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class ProjectDetailListFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.common_refresh_and_recycleview
    private val mViewModel: ProjectDetailListViewModel by lazy {
        ViewModelProvider(this).get(ProjectDetailListViewModel::class.java)
    }
    private val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        rvContent.layoutManager = LinearLayoutManager(context)
        rvContent.adapter = mAdapter
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.setOnRefreshListener {
            mViewModel.getDetailList()
        }
        rvContent.setOnLoadMoreListener {
            mViewModel.loadMoreDetailList()
        }
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        observeLoadData(mViewModel.loadingData){
            srlRefresh.isRefreshing = false
        }
        observeErrorData(mViewModel.showErrorPageData){
            mViewModel.getDetailList()
        }
        mViewModel.detailListLiveData.observe(this, Observer {
            mAdapter.setData(it)
        })
    }

    override fun onResume() {
        super.onResume()
        if (!mViewModel.isLoaded) {
            mViewModel.getDetailList()
        }
    }

    override fun initData(arguments: Bundle?) {
        arguments ?: showErrorPage(ErrorState.NO_DATA)
        if (arguments != null) {
            val responseBean = arguments.getSerializable(Constants.KEY_DATA) as TagResponseBean
            mViewModel.initData(responseBean)
            return
        }
        showErrorPage(ErrorState.NO_DATA)
    }

    override fun getLogTag(): String {
        return "DetailListFragment---${mViewModel.initData?.name}"
    }

}