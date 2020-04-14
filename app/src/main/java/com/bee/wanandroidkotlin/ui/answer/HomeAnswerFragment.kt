package com.bee.wanandroidkotlin.ui.answer

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.utils.setCommonCollectClickListener
import com.bee.wanandroidkotlin.utils.setItemClick

/**
 *
 *  问答
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class HomeAnswerFragment : BaseRefreshAndListFragment<ArticleListResponseData, ArticleListAdapter, HomeAnswerViewModel>() {
    override fun initData(arguments: Bundle?) {
        mViewModel.initOrPullRefreshDataList()
    }

    override val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }
    override val mViewModel: HomeAnswerViewModel by lazy {
        ViewModelProvider(this).get(HomeAnswerViewModel::class.java)
    }

    override fun initView() {
        super.initView()
        toolBarBuilder.hideBackIcon()
        toolBarBuilder.setTitle("问答")
    }

    override fun initListener() {
        super.initListener()
        mAdapter.setCommonCollectClickListener(this)
        mAdapter.setItemClick(this)
    }

}