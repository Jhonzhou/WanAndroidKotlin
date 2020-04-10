package com.bee.wanandroidkotlin.base

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.utils.observeErrorData
import com.bee.wanandroidkotlin.utils.observeLoadData
import com.bee.wanandroidkotlin.utils.setOnLoadMoreListener
import kotlinx.android.synthetic.main.common_refresh_and_recycleview.*

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/10
 * @Description:
 */
abstract class BaseRefreshAndListFragment<D, A : BaseRvAdapter<D>, T : BaseRefreshAndListViewModel<D>> : BaseFragment() {
    abstract val mAdapter: A
    abstract val mViewModel: T

    override fun getContentLayoutId(): Int = R.layout.common_refresh_and_recycleview

    override fun initView() {

        rvContent.layoutManager = LinearLayoutManager(context)
        rvContent.adapter = mAdapter
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        observeLoadData(mViewModel.loadingData) {
            srlRefresh.isRefreshing = false
        }
        observeErrorData(mViewModel.showErrorPageData) {
            initOrPullRefreshData()
        }
        mViewModel.mListLiveData.observe(this, Observer {
            mAdapter.setData(it)
        })
    }

    override fun initListener() {
        super.initListener()
        srlRefresh.setOnRefreshListener {
            initOrPullRefreshData()
        }
        rvContent.setOnLoadMoreListener {
            loadMoreData()
        }
    }

    open fun initOrPullRefreshData() {
        mViewModel.initOrPullRefreshDataList()
    }

    open fun loadMoreData() {
        mViewModel.loadMoreDataList()
    }

    open fun onItemClick(item: D) {

    }

    open fun onItemLongClick(item: D): Boolean = false

}