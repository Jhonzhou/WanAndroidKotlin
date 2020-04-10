package com.bee.wanandroidkotlin.ui.ground.fragment

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.base.CommonTabDetailAdapter
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.ui.ground.activity.TencentDetailListActivity
import com.bee.wanandroidkotlin.ui.ground.viewmodel.TencentTagListViewModel
import com.bee.wanandroidkotlin.utils.observeErrorData
import com.bee.wanandroidkotlin.utils.observeLoadData
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.fragment_home_tencent.*

/**
 *
 * 主页 公众号tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class TencentTagFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_tencent
    private val mDataList = arrayListOf<TagResponseBean>()
    private val mAdapter: CommonTabDetailAdapter<TagResponseBean> by lazy {
        CommonTabDetailAdapter(context!!, mDataList)
    }
    private val mViewModel: TencentTagListViewModel by lazy {
        ViewModelProvider(this).get(TencentTagListViewModel::class.java)
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        tflContent.adapter = mAdapter
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.setOnRefreshListener {
            mViewModel.getTencentList()
        }
        tflContent.setOnTagClickListener(TagFlowLayout.OnTagClickListener { view, position, parent ->
            if (position >= mDataList.size) {
                return@OnTagClickListener false
            }
            onTagClick(mDataList[position])
            true
        })
    }

    private fun onTagClick(responseBean: TagResponseBean) {
        val intent=Intent(context, TencentDetailListActivity::class.java)
        intent.putExtra(Constants.KEY_DATA,responseBean)
        startActivity(intent)
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        observeLoadData(mViewModel.loadingData) {
            srlRefresh.isRefreshing = false
        }
        observeErrorData(mViewModel.showErrorPageData) {
            mViewModel.getTencentList()
        }
        mViewModel.mTencentList.observe(this, Observer {
            mDataList.clear()
            it?.apply {
                mDataList.addAll(this)
            }
            mAdapter.notifyDataChanged()
        })
    }

    override fun initData(arguments: Bundle?) {
        mViewModel.getTencentList()
    }

}