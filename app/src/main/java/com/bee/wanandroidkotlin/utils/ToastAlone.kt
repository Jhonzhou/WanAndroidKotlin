package com.bee.wanandroidkotlin.utils

import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.bee.wanandroidkotlin.WanAndroidKotlinApplication
import java.util.logging.Logger

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/23
 * @Description:
 */
object ToastAlone {
    private var mToast: Toast? = null

    @SuppressLint("ShowToast")
    fun showToast(message: String?) {
        if (TextUtils.isEmpty(message)) {
            return
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Handler(Looper.getMainLooper()).post {
                showToastByString(message!!)
            }
        } else {
            showToastByString(message!!)
        }

    }

    @SuppressLint("ShowToast")
    fun showToast(@StringRes messageId: Int) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Handler(Looper.getMainLooper()).post {
                showToastById(messageId)
            }
        } else {
            showToastById(messageId)
        }
    }

    private fun showToastByString(message: String) {
        if (mToast == null) {
            mToast = Toast.makeText(WanAndroidKotlinApplication.mContext, message, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(message)
        }
        mToast!!.show()
    }

    private fun showToastById(@StringRes messageId: Int) {
        if (mToast == null) {
            mToast = Toast.makeText(WanAndroidKotlinApplication.mContext, messageId, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(messageId)
        }
        mToast!!.show()
    }
}