package com.bee.baselibrary.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.bee.baselibrary.R
import com.bee.baselibrary.ui.CommonToolBarBuilder
import com.bee.baselibrary.view.dialog.ui.TipDialog
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 * 基类activity
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var toolBarBuilder: CommonToolBarBuilder
    private var loadingDialog: TipDialog? = null
    lateinit var ivErrorContent: ImageView
    lateinit var tvErrorContent: TextView
    lateinit var clErrorLayout: ConstraintLayout
    lateinit var flBaseContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContent()
        initRootView()
        initView()
        initListener()
        observeViewModelData()
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
        clErrorLayout = findViewById(R.id.cl_error_page)
        ivErrorContent = findViewById(R.id.iv_error_content)
        tvErrorContent = findViewById(R.id.tv_error_hint)
        flBaseContainer = findViewById(R.id.baseContainer)
        val contentLayoutId = getContentLayoutId()
        if (contentLayoutId > 0) {
            layoutInflater.inflate(contentLayoutId, flBaseContainer)
        }
    }

    /**
     * 注册viewModel中数据对象
     */
    open fun observeViewModelData() {

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

    fun showLoadingDialog(content: String = "") {

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

}