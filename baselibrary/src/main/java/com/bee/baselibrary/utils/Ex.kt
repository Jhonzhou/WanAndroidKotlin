package com.bee.baselibrary.utils

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