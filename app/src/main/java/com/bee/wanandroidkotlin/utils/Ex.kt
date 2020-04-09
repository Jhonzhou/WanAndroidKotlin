package com.bee.wanandroidkotlin.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

fun getRandomColor(): String = "#${Integer.toHexString((Math.random() * 16777215).toInt())}"

