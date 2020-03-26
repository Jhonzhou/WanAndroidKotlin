package com.bee.wanandroidkotlin.utils

import android.content.Context
import android.util.Log

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/25
 * @Description:
 */
class CrashHandler : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {
        Log.e("exception", " thread id: ${t.name} ${e.message}", e)
        e.printStackTrace()
    }

}