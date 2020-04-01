package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.HomeFirstViewModel
import com.bee.wanandroidkotlin.ui.adapter.HomeBannerAdapter
import com.bee.wanandroidkotlin.ui.adapter.HomePageListAdapter
import kotlinx.android.synthetic.main.fragment_home_first.*

/**
 *
 * 首页tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeFirstFragment : BaseFragment() {

    override fun getContentLayoutId(): Int = R.layout.fragment_home_first
    private val mViewMode: HomeFirstViewModel by lazy {
        ViewModelProvider(activity!!).get(HomeFirstViewModel::class.java)
    }
    private val bannerAdapter: HomeBannerAdapter by lazy {
        HomeBannerAdapter(activity!!)
    }
    private val homePageListAdapter: HomePageListAdapter by lazy {
        HomePageListAdapter()
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        vpBanner.adapter = bannerAdapter
        rvContent.layoutManager = LinearLayoutManager(activity)
        rvContent.adapter = homePageListAdapter

    }

    override fun initListener() {
        super.initListener()
        srlRefresh.isVerticalScrollBarEnabled = true
        srlRefresh.setOnRefreshListener {
            mViewMode.reLoadHomePageList()

        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewMode.bannerData.observe(this, Observer { result ->
            result ?: return@Observer
            result.handlerResult {
                bannerAdapter.setData(it.data)
            }
        })
        mViewMode.homePageListData.observe(this, Observer { result ->
            result ?: return@Observer
            homePageListAdapter.setData(result)
        })
        mViewMode.loadingData.observe(this, Observer {
            if (it) {
                showLoadingDialog()
            } else {
                srlRefresh.isRefreshing = false
                hideLoadingDialog()
            }
        })
        mViewMode.reLoadHomePageList()
        mViewMode.getHomeBanner()
    }

}