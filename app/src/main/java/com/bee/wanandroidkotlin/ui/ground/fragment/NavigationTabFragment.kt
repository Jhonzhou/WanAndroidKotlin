package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.http.beans.NavigationResponseBean
import com.bee.wanandroidkotlin.ui.ground.adapter.NavigationTabAdapter
import com.bee.wanandroidkotlin.ui.ground.viewmodel.NavigationTabViewModel

/**
 *
 * 导航 fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class NavigationTabFragment : BaseRefreshAndListFragment<NavigationResponseBean, NavigationTabAdapter, NavigationTabViewModel>() {
    override val mAdapter: NavigationTabAdapter by lazy {
        NavigationTabAdapter()
    }
    override val mViewModel: NavigationTabViewModel by lazy {
        ViewModelProvider(this).get(NavigationTabViewModel::class.java)
    }
    override fun initView() {
        super.initView()
        toolBarBuilder.hideCommonBaseTitle()
    }


    override fun initData(arguments: Bundle?) {
        mViewModel.initOrPullRefreshDataList()
    }

}