package com.bee.wanandroidkotlin.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.activity.SearchActivity
import com.bee.wanandroidkotlin.ui.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.adapter.HomeBannerAdapter
import com.bee.wanandroidkotlin.ui.viewmodel.HomeFirstViewModel
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_home_first.*
import kotlin.math.abs

/**
 *
 * 首页tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeFirstFragment : BaseFragment() {

    override fun getContentLayoutId(): Int = R.layout.fragment_home_first
    private val mViewModel: HomeFirstViewModel by lazy {
        ViewModelProvider(activity!!).get(HomeFirstViewModel::class.java)
    }
    private val bannerAdapter: HomeBannerAdapter by lazy {
        HomeBannerAdapter(activity!!)
    }
    private val homePageListAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
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
            mViewModel.reLoadHomePageList()
        }
        ablLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            //解决下拉加载的滑动冲突
            srlRefresh.isEnabled = verticalOffset >= 0

            val absVerticalOffset = abs(verticalOffset)
            when {
                absVerticalOffset >= appBarLayout.totalScrollRange -> {
                    //收缩起来
                    tvTitle.text = "首页"
                    ivSearch.visibility = View.VISIBLE
                    ivArticle.visibility = View.GONE
                }
                else -> {
                    //展开
                    tvTitle.text = "文章列表"
                    ivSearch.visibility = View.GONE
                    ivArticle.visibility = View.VISIBLE
                }
            }
        })
        ivSearch.setOnClickListener {
            startActivity(Intent(activity!!,SearchActivity::class.java))
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.bannerData.observe(this, Observer { result ->
            result ?: return@Observer
            result.handlerResult {
                bannerAdapter.setData(it.data)
            }
        })
        mViewModel.homePageListData.observe(this, Observer { result ->
            result ?: return@Observer
            homePageListAdapter.setData(result)
        })
        mViewModel.loadingData.observe(this, Observer {
            if (it) {
                showLoadingDialog()
            } else {
                srlRefresh.isRefreshing = false
                hideLoadingDialog()
            }
        })
        mViewModel.reLoadHomePageList()
        mViewModel.getHomeBanner()
    }

}