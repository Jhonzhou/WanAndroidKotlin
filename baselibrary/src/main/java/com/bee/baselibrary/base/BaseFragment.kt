package com.bee.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.bee.baselibrary.R
import com.bee.baselibrary.ui.CommonToolBarBuilder
import com.bee.baselibrary.view.dialog.TipDialog
import kotlinx.android.synthetic.main.base_fragment_and_activity.*
import kotlinx.android.synthetic.main.common_error_page.*
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 *
 * 基类Fragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var toolBarBuilder: CommonToolBarBuilder
    private var loadingDialog: TipDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeCreateViewContent()
    }

    protected open fun beforeCreateViewContent() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val baseView = layoutInflater.inflate(R.layout.base_fragment_and_activity, container, false)
        initRootView(baseView)
        initView()
        return baseView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData(arguments)
    }

    private fun initRootView(rootView: View) {
        toolBarBuilder = CommonToolBarBuilder(activity!!, common_toolbar)
        val contentLayoutId = getContentLayoutId()
        if (contentLayoutId > 0) {
            layoutInflater.inflate(contentLayoutId, baseContainer)
        }

    }

    fun showLoading(content: String = "") {
        if (loadingDialog == null) {
            loadingDialog = TipDialog.Builder(context!!)
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
    abstract fun initData(savedInstanceState: Bundle?)

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