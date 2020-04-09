package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.ground.adapter.NavigationTabAdapter
import com.bee.wanandroidkotlin.ui.ground.viewmodel.NavigationTabViewModel
import com.bee.wanandroidkotlin.utils.observeErrorData
import com.bee.wanandroidkotlin.utils.observeLoadData
import kotlinx.android.synthetic.main.fragment_navigation_tab.*

/**
 *
 * 导航 fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class NavigationTabFragment : BaseFragment() {
    private val mAdapter: NavigationTabAdapter by lazy {
        NavigationTabAdapter()
    }
    private val mViewModel: NavigationTabViewModel by lazy {
        ViewModelProvider(this).get(NavigationTabViewModel::class.java)
    }

    override fun getContentLayoutId(): Int = R.layout.fragment_navigation_tab

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        rlContent.layoutManager = LinearLayoutManager(context)
        rlContent.adapter = mAdapter
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.setOnRefreshListener {
            mViewModel.getNavigationTabList()
        }
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        observeLoadData(mViewModel.loadingData) {
            srlRefresh.isRefreshing = false
        }
        observeErrorData(mViewModel.showErrorPageData) {
            mViewModel.getNavigationTabList()
        }
        mViewModel.mNavigationTabList.observe(this, Observer {
            mAdapter.setData(it)
        })
    }

    override fun initData(arguments: Bundle?) {
        mViewModel.getNavigationTabList()
    }

}