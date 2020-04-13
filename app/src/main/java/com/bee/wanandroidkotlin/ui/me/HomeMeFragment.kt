package com.bee.wanandroidkotlin.ui.me

import android.annotation.SuppressLint
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
class HomeMeFragment : BaseFragment() {
    //    private val isLogin = false
    private val isLogin by Preference(Constants.SP.SP_LOGIN, false)
    private val mViewModel: HomeMeViewModel by lazy {
        ViewModelProvider(this).get(HomeMeViewModel::class.java)
    }
    private val mAdapter: HomeMeAdapter by lazy {
        HomeMeAdapter()
    }

    override fun getContentLayoutId(): Int = R.layout.fragment_home_me

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        tvUserName.text = "攻城狮"
        tvId.text = "----"
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

                    }
                    else -> {

                    }
                }
            }

        })
        tvNeedLogin.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
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
        tvId.text = "当前积分: ${integralData?.userId ?: "----"}"
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
        }
        initListData(null)
    }


}