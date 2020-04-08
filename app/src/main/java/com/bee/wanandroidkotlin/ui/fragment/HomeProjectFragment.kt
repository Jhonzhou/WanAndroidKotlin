package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.base.BaseFragment
import com.bee.baselibrary.utils.showErrorPage
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.adapter.HomeProjectTabAdapter
import com.bee.wanandroidkotlin.ui.viewmodel.ProjectHomeViewModel
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
        toolBarBuilder.setTitle(R.string.s_project)
        toolBarBuilder.hideCommonBaseTitle()
        vpContent.adapter = mAdapter

        TabLayoutMediator(tlTitle, vpContent,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text="title$position text"
                    Log.e("HomeProjectFragment","TabConfigurationStrategy position:${tab.text}")
//
//                    val responseBean = mAdapter.getItem(position)
//                    responseBean?.apply {
//                        tab.text = name
//
//                    }
                }).attach()
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        mViewModel.loadingData.observe(this, Observer {
            if (it){
                showLoadingDialog()
            }else{
                hideLoadingDialog()
            }
        })
        mViewModel.showErrorPageData.observe(this, Observer {
            when (it) {
                ErrorState.NET_ERROR -> {
                    showErrorPage(it) {
                        mViewModel.getProjectTabList()
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
        mViewModel.mProjectTagList.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                showErrorPage(ErrorState.NO_DATA)
                return@Observer
            }
//            tlTitle.removeAllTabs()
//            for (responseBean in it) {
//                tlTitle.addTab(tlTitle.newTab())
//            }
            mAdapter.setData(it)
            tlTitle.getTabAt(0)?.text="tlTitle.getTabAt0"
            tlTitle.getTabAt(1)?.text="tlTitle.getTabAt1"
            tlTitle.getTabAt(2)?.text="tlTitle.getTabAt2"
            tlTitle.getTabAt(3)?.text="tlTitle.getTabAt3"
        })
    }

    override fun initData(arguments: Bundle?) {
//        mViewModel.getProjectTabListFromLocal()
        mViewModel.getProjectTabList()
    }

}