package com.bee.wanandroidkotlin.ui.me

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.beans.HomeMeItemBean
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.ui.me.adapter.HomeMeAdapter
import com.bee.wanandroidkotlin.ui.me.viewmodel.SettingViewModel
import kotlinx.android.synthetic.main.activity_setting.*

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
class SettingActivity : BaseActivity() {
    private var isLogin by Preference(Constants.SP.SP_LOGIN, false)
    private val mViewModel: SettingViewModel by lazy {
        ViewModelProvider(this).get(SettingViewModel::class.java)
    }
    private val mAdapter: HomeMeAdapter by lazy {
        HomeMeAdapter()
    }

    override fun getContentLayoutId(): Int = R.layout.activity_setting
    override fun initView() {
        toolBarBuilder.setTitle("设置")
        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.adapter = mAdapter
    }

    override fun initData(intent: Intent?) {
        val dataList = arrayListOf<HomeMeItemBean>()
        dataList.add(HomeMeItemBean( "清除缓存"))
        dataList.add(HomeMeItemBean( "版本", isShowDivider = false))
        dataList.add(HomeMeItemBean( "作者", isShowTag = true))
        dataList.add(HomeMeItemBean( "项目"))
        dataList.add(HomeMeItemBean("版权声明"))
        mAdapter.setData(dataList)
        btnLogout.visibility = if (isLogin) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }


    override fun observeViewModelData() {
        super.observeViewModelData()
        mViewModel.loadingData.observe(this, Observer {
            if (it) {
                showLoadingDialog()
            } else {
                hideLoadingDialog()
            }
        })
        mViewModel.mLogoutResult.observe(this, Observer {
            if (it) {
                setResult(Activity.RESULT_OK)
                isLogin=false
                btnLogout.visibility = View.GONE
            } else {
                btnLogout.visibility = View.VISIBLE
            }
        })
    }

    override fun initListener() {
        super.initListener()
        btnLogout.setOnClickListener {
            mViewModel.logout()
        }
    }


}