package com.bee.wanandroidkotlin.ui.me

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.base.BaseFragment
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.beans.HomeMeItemBean
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.IntegralResponseData
import com.bee.wanandroidkotlin.ui.common.activity.LoginActivity
import com.bee.wanandroidkotlin.ui.me.adapter.HomeMeAdapter
import com.bee.wanandroidkotlin.ui.me.viewmodel.HomeMeViewModel
import kotlinx.android.synthetic.main.fragment_home_me.*

/**
 *
 * 首页 我的tabFragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
private const val SETTING_REQUEST_CODE = 100

class HomeMeFragment : BaseFragment() {

    private var isLogin by Preference(Constants.SP.SP_LOGIN, false)
    private val mViewModel: HomeMeViewModel by lazy {
        ViewModelProvider(this).get(HomeMeViewModel::class.java)
    }
    private val mAdapter: HomeMeAdapter by lazy {
        HomeMeAdapter()
    }

    override fun getContentLayoutId(): Int = R.layout.fragment_home_me

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        rvContent.layoutManager = LinearLayoutManager(context)
        rvContent.adapter = mAdapter

    }

    override fun initData(arguments: Bundle?) {
        initUserData()
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
        mViewModel.mIntegralLiveData.observe(this, Observer {
            initListData(it)
        })
    }

    override fun initListener() {
        super.initListener()
        mAdapter.setOnItemClickListener(object : BaseRvAdapter.OnItemClickListener<HomeMeItemBean> {
            override fun onItemClick(baseViewHolder: BaseViewHolder, position: Int, item: HomeMeItemBean) {
                when (item.id) {
                    HomeMeItemBean.ID.INTEGRAL -> {
                        //积分
                    }
                    HomeMeItemBean.ID.COLLECT -> {
                        //收藏
                    }
                    HomeMeItemBean.ID.ARTICLE -> {
                        //我的文章
                    }
                    HomeMeItemBean.ID.SETTING -> {
                        //设置
                        val intent = Intent(context, SettingActivity::class.java)
                        startActivityForResult(intent, SETTING_REQUEST_CODE)
                    }
                    else -> {

                    }
                }
            }

        })
        tvNeedLogin.setOnClickListener {
            LoginActivity.startForResult(this, SETTING_REQUEST_CODE)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initListData(integralData: IntegralResponseData?) {
        val dataList = arrayListOf<HomeMeItemBean>()
        if (isLogin) {
            dataList.add(HomeMeItemBean(HomeMeItemBean.ID.INTEGRAL, "我的积分", isShowTag = true,
                    rightText = "${integralData?.coinCount ?: "0"}"))
            dataList.add(HomeMeItemBean(HomeMeItemBean.ID.COLLECT, "我的收藏", isShowTag = false))
            dataList.add(HomeMeItemBean(HomeMeItemBean.ID.ARTICLE, "我的文章", isShowTag = false, isShowDivider = false))
        }
        dataList.add(HomeMeItemBean(HomeMeItemBean.ID.SETTING, "设置", isShowTag = true))
        mAdapter.setData(dataList)
        tvUserName.text = integralData?.username ?: ""
        tvId.text = "${integralData?.userId ?: "----"}"
    }

    private fun initUserData() {
        if (isLogin) {
            tvNeedLogin.visibility = View.GONE
            rlUserInfo.visibility = View.VISIBLE
        } else {
            tvNeedLogin.visibility = View.VISIBLE
            rlUserInfo.visibility = View.GONE
        }

        if (isLogin) {
            mViewModel.getIntegralFromLocal()
            mViewModel.getIntegral()
        } else {
            initListData(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTING_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                initUserData()
            }
        }
    }


}