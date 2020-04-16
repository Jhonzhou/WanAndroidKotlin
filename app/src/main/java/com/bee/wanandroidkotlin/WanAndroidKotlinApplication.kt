package com.bee.wanandroidkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.utils.CrashHandler
import com.tencent.bugly.crashreport.CrashReport

/**
 */
class WanAndroidKotlinApplication : MultiDexApplication() {

    companion object {
        var mTestActivity: Activity? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context

        fun getGlobalContext() {
            mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

        Preference.initContext(applicationContext)
        //异常捕获
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler())
        CrashReport.initCrashReport(applicationContext, "849d8f661b", false)

    }


}