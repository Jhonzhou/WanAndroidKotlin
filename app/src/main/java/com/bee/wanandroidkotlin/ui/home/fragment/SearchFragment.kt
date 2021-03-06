package com.bee.wanandroidkotlin.ui.home.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.base.CommonTabDetailAdapter
import com.bee.wanandroidkotlin.http.beans.HotTagResponseBean
import com.bee.wanandroidkotlin.ui.common.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.home.viewmodel.SearchViewModel
import com.bee.wanandroidkotlin.utils.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.title_search_input_layout.*

/**
 *
 *搜索
 * @author: JhonZhou
 * @date:  2020/4/2
 * @Description:
 */
class SearchFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_search
    private val hotList = arrayListOf<HotTagResponseBean>()
    private val mHotTagListAdapter by lazy {
        CommonTabDetailAdapter(activity!!, hotList)
    }
    private val mViewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    private val mAdapter: ArticleListAdapter by lazy {
        ArticleListAdapter()
    }

    override fun initView() {
        val titleSearchView = LayoutInflater.from(context).inflate(R.layout.title_search_input_layout, null)
        toolBarBuilder.hideBackIcon()
        toolBarBuilder.addView(titleSearchView)
        rvContent.layoutManager = LinearLayoutManager(context)
        rvContent.adapter = mAdapter
        tflHotContent.adapter = mHotTagListAdapter
    }


    override fun initListener() {
        super.initListener()
        srlRefresh.setOnRefreshListener {
            mViewModel.search(etSearch.text.toString())
        }
        mAdapter.setCommonCollectClickListener(this)
        mAdapter.setItemClick(this)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val key = s?.toString()
                ivDelete.visibility = if (TextUtils.isEmpty(key)) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
                mViewModel.search(key)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        etSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    mViewModel.search(v?.text?.toString())
                    true
                }
                else -> {
                    false
                }
            }
        }
        ivDelete.setOnClickListener {
            etSearch.setText("")
        }
        ivBack.setOnClickListener {
            activity?.apply {
                onBackPressed()
            }
        }
        rvContent.setOnLoadMoreListener {
            mViewModel.loadMoreSearchResult(etSearch.text.toString())
        }
        tflHotContent.setOnTagClickListener { view, position, parent ->
            if (position >= hotList.size) {
                return@setOnTagClickListener false
            }
            mViewModel.search(hotList[position].name)
            true
        }
    }

    override fun observeViewModelData() {
        super.observeViewModelData()
        mViewModel.searchResultData.observe(this, Observer {
            if (it == null) {
                //null 是情况界面
                showCorrectPage()
                showCommonPage(true)
            } else {
                showCommonPage(false)
                mAdapter.setData(it)
                if (it.isEmpty()) {
                    ToastAlone.showToast("搜索无结果")
                    srlRefresh.isEnabled = false
                } else {
                    srlRefresh.isEnabled = true
                }
            }
        })
        observeLoadData(mViewModel.loadingData) {
            srlRefresh.isRefreshing = false
        }
        observeErrorData(mViewModel.showErrorPageData) {
            mViewModel.search(etSearch.text.toString())
        }
        mViewModel.hotListLiveData.observe(this, Observer {
            refreshHotList(it)
        })
    }

    private fun refreshHotList(dataList: ArrayList<HotTagResponseBean>?) {
        dataList?.apply {
            hotList.clear()
            hotList.addAll(this)
            mHotTagListAdapter.notifyDataChanged()
        }
    }

    private fun showCommonPage(isShowCommonPage: Boolean) {
        if (isShowCommonPage) {
            srlRefresh.visibility = View.GONE
            llCommonPage.visibility = View.VISIBLE
        } else {
            srlRefresh.visibility = View.VISIBLE
            llCommonPage.visibility = View.GONE

        }
    }

    override fun initData(arguments: Bundle?) {
        mViewModel.getHotList()
        showCommonPage(true)
    }

}