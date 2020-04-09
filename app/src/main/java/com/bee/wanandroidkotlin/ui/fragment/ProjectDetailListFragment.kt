package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.base.BaseFragment
import com.bee.baselibrary.utils.setOnLoadMoreListener
import com.bee.baselibrary.utils.showErrorPage
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.ProjectTabResponseBean
import com.bee.wanandroidkotlin.ui.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.viewmodel.ProjectDetailListViewModel
import kotlinx.android.synthetic.main.fragment_project_detail_list.*

/**
 *
 *  项目界面详情列表fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class ProjectDetailListFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_project_detail_list
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
        mViewModel.loadingData.observe(this, Observer {
            if (it) {
                showLoadingDialog()
            } else {
                hideLoadingDialog()
                srlRefresh.isRefreshing = false
            }
        })
        mViewModel.showErrorPageData.observe(this, Observer {
            when (it) {
                ErrorState.NET_ERROR -> {
                    Log.e(getLogTag(),"showErrorPageData state: net error")
                    showErrorPage(it) {
                        mViewModel.getDetailList()
                        showCorrectPage()
                    }
                }
                ErrorState.NO_DATA -> {
                    Log.e(getLogTag(),"showErrorPageData state: net error")
                    showErrorPage(it)
                }
                else -> {
                    Log.e(getLogTag(),"showErrorPageData state: net error")
                }
            }
        })
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
            val responseBean = arguments.getSerializable(Constants.KEY_DATA) as ProjectTabResponseBean
            mViewModel.initData(responseBean)
            return
        }
        showErrorPage(ErrorState.NO_DATA)
    }

    override fun getLogTag(): String {
        return "DetailListFragment---${mViewModel.initData?.name}"
    }

}