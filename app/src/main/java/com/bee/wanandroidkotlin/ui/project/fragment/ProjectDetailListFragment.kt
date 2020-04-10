package com.bee.wanandroidkotlin.ui.project.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.baselibrary.ErrorState
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.ArticleListResponseData
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.project.viewmodel.ProjectDetailListViewModel
import com.bee.wanandroidkotlin.utils.showErrorPage

/**
 *
 *  项目界面详情列表fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class ProjectDetailListFragment : BaseRefreshAndListFragment<ArticleListResponseData,ArticleListAdapter,ProjectDetailListViewModel>() {
    override val mViewModel: ProjectDetailListViewModel by lazy {
        ViewModelProvider(this).get(ProjectDetailListViewModel::class.java)
    }
    override val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }

    override fun initView() {
        super.initView()
        toolBarBuilder.hideCommonBaseTitle()
    }


    override fun onResume() {
        super.onResume()
        if (!mViewModel.isLoaded) {
            mViewModel.initOrPullRefreshDataList()
        }
    }

    override fun initData(arguments: Bundle?) {
        arguments ?: showErrorPage(ErrorState.NO_DATA)
        if (arguments != null) {
            val responseBean = arguments.getSerializable(Constants.KEY_DATA) as TagResponseBean
            mViewModel.initData(responseBean)
            return
        }
        showErrorPage(ErrorState.NO_DATA)
    }

    override fun getLogTag(): String {
        return "DetailListFragment---${mViewModel.initData?.name}"
    }

}