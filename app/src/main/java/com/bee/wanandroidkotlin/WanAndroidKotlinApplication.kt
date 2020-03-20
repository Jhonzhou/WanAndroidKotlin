package com.bee.wanandroidkotlin

import android.app.Application
import com.bee.baselibrary.utils.Preference

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/20
 * @Description:
 */
class WanAndroidKotlinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Preference.initContext(applicationContext)
    }

}