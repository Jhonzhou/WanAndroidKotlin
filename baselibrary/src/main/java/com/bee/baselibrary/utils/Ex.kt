package com.bee.baselibrary.utils

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bee.baselibrary.ErrorState
import com.bee.baselibrary.R
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.base.BaseFragment
import kotlinx.android.synthetic.main.base_fragment_and_activity.*
import kotlinx.android.synthetic.main.common_error_page.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/26
 * @Description:
 */
fun tryCatch(catchBlock: (Throwable) -> Unit = {}, tryBlock: () -> Unit) {
    try {
        tryBlock()
    } catch (_: CancellationException) {

    } catch (t: Throwable) {
        catchBlock(t)
    }
}

fun launchMain(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) {
        block()
    }
}

fun launchIo(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        block()
    }
}

fun RecyclerView.setOnLoadMoreListener(block: (RecyclerView) -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastVisibleItemPosition() == (layoutManager.itemCount - 1)) {
                    //表示滑动到最后了
                    block(recyclerView)
                }
            }
        }
    })
}

fun BaseFragment.showErrorPage(state: ErrorState, textClickListener: () -> Unit = {}) {
    val errorContent: String
    @DrawableRes val errorImage: Int
    when (state) {
        ErrorState.NET_ERROR -> {
            errorContent = "网络异常"
            errorImage = R.drawable.ic_net_error
        }
        ErrorState.NO_DATA -> {
            errorContent = "无数据"
            errorImage = R.drawable.ic_no_data
        }
        else -> {
            return
        }
    }
    baseContainer.visibility = View.GONE
    cl_error_page.visibility = View.VISIBLE
    tv_error_hint.text = errorContent
    iv_error_content.setImageResource(errorImage)
    tv_error_hint.setOnClickListener {
        textClickListener()
    }
}

fun BaseActivity.showErrorPage(state: ErrorState, textClickListener: () -> Unit = {}) {
    val errorContent: String
    @DrawableRes val errorImage: Int
    when (state) {
        ErrorState.NET_ERROR -> {
            errorContent = "网络异常"
            errorImage = R.drawable.ic_net_error
        }
        ErrorState.NO_DATA -> {
            errorContent = "无数据"
            errorImage = R.drawable.ic_no_data
        }
        else -> {
            return
        }
    }
    baseContainer.visibility = View.GONE
    cl_error_page.visibility = View.VISIBLE
    tv_error_hint.text = errorContent
    iv_error_content.setImageResource(errorImage)
    tv_error_hint.setOnClickListener {
        textClickListener()
    }
}