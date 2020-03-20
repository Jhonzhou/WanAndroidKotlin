package com.bee.baselibrary.ui

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import com.bee.baselibrary.R

/**
 *
 *
 * @author zhouxiaojun
 * @date  2019/6/17
 */
class CommonToolBarBuilder(activity: Activity, toolbar: Toolbar) {
    private val defaultBackIconResId = R.drawable.ic_back

    private var tvCenterTitle: TextView? = null
    private var tvRight: TextView? = null
    private var ivRight: ImageView? = null
    private var ivRightTwo: ImageView? = null
    private var mActivity: Activity = activity
    private var toolbar: Toolbar = toolbar

    init {

        tvCenterTitle = toolbar.findViewById(R.id.tv_center_title)
        tvRight = toolbar.findViewById(R.id.tv_right)
        ivRight = toolbar.findViewById(R.id.iv_right)
        ivRightTwo = toolbar.findViewById(R.id.iv_right_two)
        toolbar.setNavigationIcon(defaultBackIconResId)
        toolbar.setNavigationOnClickListener {
            mActivity.onBackPressed()
        }
    }

    fun hideBackIcon(): CommonToolBarBuilder {
        toolbar.navigationIcon = null
        return this
    }

    fun setBackCallBack(clickListener: View.OnClickListener?): CommonToolBarBuilder {
        toolbar.setNavigationOnClickListener(clickListener)
        return this
    }

    fun setTitle(title: String, @ColorInt textColor: Int = -1, clickListener: View.OnClickListener? = null): CommonToolBarBuilder {
        tvCenterTitle?.let {
            it.visibility = View.VISIBLE
            it.text = title
            if (textColor != -1) {
                it.setTextColor(textColor)
            }
            it.setOnClickListener(clickListener)
        }
        return this
    }

    fun setRightText(rightText: String, @ColorInt textColor: Int = -1, clickListener: View.OnClickListener? = null): CommonToolBarBuilder {
        tvRight?.let {
            it.visibility = View.VISIBLE
            it.text = rightText
            if (textColor != -1) {
                it.setTextColor(textColor)
            }
            it.setOnClickListener(clickListener)
        }
        return this
    }

    fun setRightImage(@DrawableRes imageRes: Int, clickListener: View.OnClickListener? = null): CommonToolBarBuilder {
        ivRight?.let {
            it.visibility = View.VISIBLE
            it.setImageResource(imageRes)
            it.setOnClickListener(clickListener)
        }
        return this
    }

    fun setRightSecondImage(@DrawableRes imageRes: Int, clickListener: View.OnClickListener? = null): CommonToolBarBuilder {
        ivRightTwo?.let {
            it.visibility = View.VISIBLE
            it.setImageResource(imageRes)
            it.setOnClickListener(clickListener)
        }
        return this
    }
}