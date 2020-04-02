package com.bee.baselibrary.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bee.baselibrary.R
import com.bee.baselibrary.ui.CommonToolBarBuilder
import com.bee.baselibrary.view.dialog.TipDialog
import kotlinx.android.synthetic.main.base_fragment_and_activity.*
import kotlinx.android.synthetic.main.common_error_page.*
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 * 基类activity
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var toolBarBuilder: CommonToolBarBuilder
    private var loadingDialog: TipDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContent()
        initRootView()
        initView()
        initListener()
        initData(intent)
    }

    protected open fun beforeSetContent() {
        //沉侵式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun getMActivity(): Activity = this

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initData(intent)
    }

    private fun initRootView() {
        setContentView(R.layout.base_fragment_and_activity)
        toolBarBuilder = CommonToolBarBuilder(this, common_toolbar)
        val contentLayoutId = getContentLayoutId()
        if (contentLayoutId > 0) {
            layoutInflater.inflate(contentLayoutId, baseContainer)
        }
    }

    fun displayFragment(fragment: Fragment, isAddToBack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            if (!fragment.isAdded) {
                add(R.id.baseContainer, fragment)
                if (isAddToBack) {
                    addToBackStack(null)
                }
            }
            if (fragment.isHidden) {
                show(fragment)
            }
        }.commit()
    }

    fun showLoading(content: String = "") {

        if (loadingDialog == null) {
            loadingDialog = TipDialog.Builder(this)
                    .setIconType(TipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(content)
                    .create()
            loadingDialog?.setCancelable(true)
            loadingDialog?.setCanceledOnTouchOutside(true)
        }
        loadingDialog?.show()
    }

    fun hideLoadingDialog() {
        loadingDialog?.apply {
            if (isShowing) {
                dismiss()
            }
        }
    }

    /**
     * 根布局
     */
    @LayoutRes
    abstract fun getContentLayoutId(): Int

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData(intent: Intent?)

    /**
     * 初始化监听
     */
    open fun initListener() {

    }


    fun hideErrorPage() {
        cl_error_page.visibility = View.GONE
    }

    /**
     * 显示错误界面
     */
    fun showErrorPage(errorContent: String = "网络异常", @DrawableRes errorImage: Int = R.drawable.ic_net_error) {
        cl_error_page.visibility = View.VISIBLE
        tv_error_hint.text = errorContent
        iv_error_content.setImageResource(errorImage)
    }

}