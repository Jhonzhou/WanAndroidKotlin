package com.bee.wanandroidkotlin.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.HomeFirstViewModel
import com.bee.wanandroidkotlin.ui.adapter.HomeBannerAdapter
import com.bee.wanandroidkotlin.utils.ToastAlone
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

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
//        toolBarBuilder.setTitle(R.string.s_home)
        vpBanner.adapter = bannerAdapter
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.isVerticalScrollBarEnabled = true
        srlRefresh.setOnRefreshListener {
            ToastAlone.showToast("下拉刷新")
            tvTitle.text = "${tvTitle.text}+1"
            srlRefresh.isRefreshing = false
        }


    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewMode.bannerData.observe(this, Observer { result ->
            result ?: return@Observer
            result.handlerResult {
                bannerAdapter.setData(it.data)
            }
        })
        mViewMode.getHomeBanner()
    }

}