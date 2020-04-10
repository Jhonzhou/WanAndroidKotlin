package com.bee.wanandroidkotlin.ui.ground.fragment

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bee.wanandroidkotlin.base.BaseRefreshAndListFragment
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.listener.TagClickListener
import com.bee.wanandroidkotlin.ui.ground.activity.TagDetailListActivity
import com.bee.wanandroidkotlin.ui.ground.adapter.SystemTagListAdapter
import com.bee.wanandroidkotlin.ui.ground.viewmodel.SystemTagViewModel

/**
 *
 * 体系 fragment
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class SystemTabFragment : BaseRefreshAndListFragment<TagResponseBean, SystemTagListAdapter, SystemTagViewModel>() {
    override val mAdapter: SystemTagListAdapter by lazy {
        SystemTagListAdapter()
    }
    override val mViewModel: SystemTagViewModel by lazy {
        ViewModelProvider(this).get(SystemTagViewModel::class.java)
    }

    override fun initListener() {
        super.initListener()
        mAdapter.setTagClickListener(object : TagClickListener<TagResponseBean> {
            override fun onClick(item: TagResponseBean) {
                val intent = Intent(context, TagDetailListActivity::class.java)
                intent.putExtra(Constants.KEY_DATA, item)
                startActivity(intent)
            }

        })
    }

    override fun initView() {
        super.initView()
        toolBarBuilder.hideCommonBaseTitle()
    }

    override fun initData(arguments: Bundle?) {
        mViewModel.initOrPullRefreshDataList()
    }

}