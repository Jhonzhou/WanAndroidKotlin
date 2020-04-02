package com.bee.wanandroidkotlin.ui.fragment

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
import com.bee.wanandroidkotlin.ui.adapter.ArticleListAdapter
import com.bee.wanandroidkotlin.ui.viewmodel.SearchViewModel
import com.bee.wanandroidkotlin.utils.ToastAlone
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
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val key = s?.toString()
                ivDelete.visibility = if (TextUtils.isEmpty(key)) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        etSearch.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    doSearch(v?.text?.toString())
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
    }

    private fun doSearch(keyWord: String?) {
        mViewModel.search(keyWord)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel.searchResultData.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                ToastAlone.showToast("搜索无结果")
                rvContent.visibility = View.GONE
                srlRefresh.isEnabled=false
            } else {
                mAdapter.setData(it)
                rvContent.visibility = View.VISIBLE
                srlRefresh.isEnabled=false
            }
        })
        mViewModel.loadingData.observe(this, Observer {
            if (it) {
                showLoadingDialog()
            } else {
                srlRefresh.isRefreshing = false
                hideLoadingDialog()
            }
        })
    }

}