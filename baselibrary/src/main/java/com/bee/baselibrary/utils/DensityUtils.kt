package com.bee.baselibrary.utils

import android.content.res.Resources

/**
 * 单位换算工具类
 */
class DensityUtils private constructor() {

    init {
        /** cannot be instantiated  */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * dp转px
         */
        fun dp2px(dpVal: Float): Int {
            val scale = Resources.getSystem().displayMetrics.density
            return (dpVal * scale + 0.5f).toInt()
        }

        /**
         * sp转px
         */
        fun sp2px(spVal: Float): Int {
            val fontScale = Resources.getSystem().displayMetrics.scaledDensity
            return (spVal * fontScale + 0.5f).toInt()
        }

        /**
         * px转dp
         */
        fun px2dp(pxVal: Float): Float {
            val scale = Resources.getSystem().displayMetrics.density
            return pxVal / scale
        }

        /**
         * px转sp
         */
        fun px2sp(pxVal: Float): Float {
            return pxVal / Resources.getSystem().displayMetrics.scaledDensity
        }
    }

}
