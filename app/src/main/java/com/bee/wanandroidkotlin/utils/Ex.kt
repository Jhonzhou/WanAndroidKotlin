package com.bee.wanandroidkotlin.utils

import kotlinx.coroutines.CancellationException

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/26
 * @Description:
 */
inline fun tryCatch(catchBlock: (Throwable) -> Unit = {}, tryBlock: () -> Unit) {
    try {
        tryBlock()
    } catch (_: CancellationException) {

    } catch (t: Throwable) {
        catchBlock(t)
    }
}