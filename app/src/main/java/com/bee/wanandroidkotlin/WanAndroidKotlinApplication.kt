package com.bee.wanandroidkotlin

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.utils.CrashHandler

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
    }


}