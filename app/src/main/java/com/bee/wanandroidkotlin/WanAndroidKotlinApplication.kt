package com.bee.wanandroidkotlin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.utils.CrashHandler
import com.bee.wanandroidkotlin.utils.changeToBlack
import com.tencent.bugly.crashreport.CrashReport

/**
 */
class WanAndroidKotlinApplication : MultiDexApplication() {

    companion object {
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

        //一键置灰功能
        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activity.changeToBlack()
            }

            override fun onActivityResumed(activity: Activity) {
            }

        })

    }


}