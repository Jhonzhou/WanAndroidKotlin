package com.bee.baselibrary.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.bee.baselibrary.R
import com.bee.baselibrary.utils.DensityUtils
import com.bee.baselibrary.view.LoadingView
import com.bee.baselibrary.view.dialog.TipDialog.Builder.IconType

/**
 * 提示性对话框
 * 提供一个浮层展示在屏幕中间, 一般使用 [TipDialog.Builder] 或 [TipDialog.CustomBuilder] 生成。
 *
 *  * [TipDialog.Builder] 提供了一个图标和一行文字的样式, 其中图标有几种类型可选, 见 [TipDialog.Builder.IconType]
 *  * [TipDialog.CustomBuilder] 支持传入自定义的 layoutResId, 达到自定义 TipDialog 的效果。
 *
 * Created by Administrator on 2018/1/17.
 */

class TipDialog : Dialog {
    constructor(context: Context) : this(context, R.style.TipDialog)
    constructor(context: Context, themeResId: Int = R.style.TipDialog) : super(context, themeResId)

    init {
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialogWidth()
    }

    private fun initDialogWidth() {
        val window = window
        if (window != null) {
            val wmLp = window.attributes
            wmLp.width = ViewGroup.LayoutParams.MATCH_PARENT
            window.attributes = wmLp
        }
    }

    /**
     * 生成默认的 [TipDialog]
     *
     *
     * 提供了一个图标和一行文字的样式, 其中图标有几种类型可选。见 [IconType]
     *
     *
     * @see CustomBuilder
     */
    class Builder(private val mContext: Context) {

        @IconType
        private var mCurrentIconType = ICON_TYPE_NOTHING

        private var mTipWord: CharSequence? = null

        @DrawableRes
        private var backgroundResId: Int = 0

        @IntDef(ICON_TYPE_NOTHING, ICON_TYPE_LOADING, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO)
        @Retention(AnnotationRetention.SOURCE)
        annotation class IconType

        /**
         * 设置 icon 显示的内容
         *
         * @see IconType
         */
        fun setIconType(@IconType iconType: Int): Builder {
            mCurrentIconType = iconType
            return this
        }

        /**
         * 设置显示的文案
         */
        fun setTipWord(tipWord: CharSequence): Builder {
            mTipWord = tipWord
            return this
        }

        /**
         * 设置弹窗北京资源地址
         */
        fun setBackgroudResId(@DrawableRes backgroundResId: Int): Builder {
            this.backgroundResId = backgroundResId
            return this
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 [Dialog.show] 方法
         *
         * @return 创建的 Dialog
         */
        fun create(): TipDialog {
            val dialog = TipDialog(mContext)
            dialog.setContentView(R.layout.tip_dialog_layout)
            val contentWrap = dialog.findViewById<View>(R.id.contentWrap) as ViewGroup

            if (mCurrentIconType == ICON_TYPE_LOADING) {
                val loadingView = LoadingView(mContext)
                loadingView.setColor(Color.GRAY)
                loadingView.setSize(DensityUtils.dp2px(22F))
                val loadingViewLP = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                loadingView.layoutParams = loadingViewLP
                contentWrap.addView(loadingView)
                contentWrap.setBackgroundColor(mContext.resources.getColor(R.color.transparent))

            } else if (mCurrentIconType == ICON_TYPE_SUCCESS || mCurrentIconType == ICON_TYPE_FAIL || mCurrentIconType == ICON_TYPE_INFO) {
                val imageView = ImageView(mContext)
                val imageViewLP = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                imageView.layoutParams = imageViewLP

                if (mCurrentIconType == ICON_TYPE_SUCCESS) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_notify_done))
                } else if (mCurrentIconType == ICON_TYPE_FAIL) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_notify_error))
                } else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_notify_info))
                }
                contentWrap.addView(imageView)

            }

            if (mTipWord != null && mTipWord!!.isNotEmpty()) {
                val tipView = TextView(mContext)
                val tipViewLP = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

                if (mCurrentIconType != ICON_TYPE_NOTHING) {
                    tipViewLP.topMargin = DensityUtils.dp2px(12F)
                }
                tipView.layoutParams = tipViewLP

                tipView.ellipsize = TextUtils.TruncateAt.END
                tipView.gravity = Gravity.CENTER
                tipView.maxLines = 2
                tipView.setTextColor(ContextCompat.getColor(mContext, R.color.white))
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
                tipView.text = mTipWord

                contentWrap.addView(tipView)
            }
            return dialog
        }

        companion object {
            /**
             * 不显示任何icon
             */
            const val ICON_TYPE_NOTHING = 0
            /**
             * 显示 Loading 图标
             */
            const val ICON_TYPE_LOADING = 1
            /**
             * 显示成功图标
             */
            const val ICON_TYPE_SUCCESS = 2
            /**
             * 显示失败图标
             */
            const val ICON_TYPE_FAIL = 3
            /**
             * 显示信息图标
             */
            const val ICON_TYPE_INFO = 4
        }

    }

    /**
     * 传入自定义的布局并使用这个布局生成 TipDialog
     */
    class CustomBuilder(private val mContext: Context) {
        private var mContentLayoutId: Int = 0

        fun setContent(@LayoutRes layoutId: Int): CustomBuilder {
            mContentLayoutId = layoutId
            return this
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 [Dialog.show] 方法
         *
         * @return 创建的 Dialog
         */
        fun create(): TipDialog {
            val dialog = TipDialog(mContext)
            dialog.setContentView(R.layout.tip_dialog_layout)
            val contentWrap = dialog.findViewById<View>(R.id.contentWrap) as ViewGroup
            LayoutInflater.from(mContext).inflate(mContentLayoutId, contentWrap, true)
            return dialog
        }
    }
}
