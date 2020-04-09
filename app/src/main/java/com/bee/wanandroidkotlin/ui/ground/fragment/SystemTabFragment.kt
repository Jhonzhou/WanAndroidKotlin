package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.ground.adapter.SystemTagListAdapter
import com.bee.wanandroidkotlin.ui.ground.viewmodel.SystemTagViewModel
import com.bee.wanandroidkotlin.utils.showErrorPage
import kotlinx.android.synthetic.main.fragment_system_tab.*

/**
 *
 * 体系 fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class SystemTabFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_system_tab
    private val mAdapter: SystemTagListAdapter by lazy {
        SystemTagListAdapter()
    }
    private val mViewModel: SystemTagViewModel by lazy {
        ViewModelProvider(this).get(SystemTagViewModel::class.java)
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        rvContent.layoutManager = LinearLayoutManager(context)
        rvContent.adapter=mAdapter
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.setOnRefreshListener {
            mViewModel.getTreeTabList()
        }
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        mViewModel.loadingData.observe(this, Observer {
            if (it) {
                showLoadingDialog()
            } else {
                hideLoadingDialog()
                srlRefresh.isRefreshing=false
            }
        })
        mViewModel.showErrorPageData.observe(this, Observer {
            when (it) {
                ErrorState.NET_ERROR -> {
                    showErrorPage(it) {
                        mViewModel.getTreeTabList()
                        showCorrectPage()
                    }
                }
                ErrorState.NO_DATA -> {
                    showErrorPage(it)
                }
                else -> {
                }
            }
        })
        mViewModel.mSystemTagList.observe(this, Observer {
            mAdapter.setData(it)
        })
    }

    override fun initData(arguments: Bundle?) {
        mViewModel.getTreeTabList()
    }

}