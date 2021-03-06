package com.bee.wanandroidkotlin.ui.project.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.project.adapter.HomeProjectTabAdapter
import com.bee.wanandroidkotlin.ui.project.viewmodel.ProjectHomeViewModel
import com.bee.wanandroidkotlin.utils.observeErrorData
import com.bee.wanandroidkotlin.utils.observeLoadData
import com.bee.wanandroidkotlin.utils.showErrorPage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home_project.*

/**
 *
 *  主页 项目tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeProjectFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_project
    private val mAdapter: HomeProjectTabAdapter by lazy {
        HomeProjectTabAdapter(this)
    }
    private val mViewModel: ProjectHomeViewModel by lazy {
        ViewModelProvider(this).get(ProjectHomeViewModel::class.java)
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        vpContent.adapter = mAdapter
        TabLayoutMediator(tlTitle, vpContent,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    val responseBean = mAdapter.getItem(position)
                    responseBean?.apply {
                        tab.text = name
                    }
                }).attach()
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        observeLoadData(mViewModel.loadingData)
        observeErrorData(mViewModel.showErrorPageData) {
            mViewModel.getProjectTabList()
        }
        mViewModel.mProjectTagList.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                showErrorPage(ErrorState.NO_DATA)
                return@Observer
            }
            mAdapter.setData(it)
        })
    }

    override fun initData(arguments: Bundle?) {
        mViewModel.getProjectTabListFromLocal()
        mViewModel.getProjectTabList()
    }

}