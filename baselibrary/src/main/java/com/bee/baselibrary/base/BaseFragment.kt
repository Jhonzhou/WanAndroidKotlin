package com.bee.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.bee.baselibrary.R
import com.bee.baselibrary.ui.CommonToolBarBuilder
import com.bee.baselibrary.view.dialog.TipDialog
import kotlinx.android.synthetic.main.base_fragment_and_activity.*
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


    protected open fun beforeCreateViewContent() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        beforeCreateViewContent()
        return layoutInflater.inflate(R.layout.base_fragment_and_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRootView(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        observeViewModelData()
        initData(arguments)
    }
    /**
     * 注册viewModel中数据对象
     */
    open fun observeViewModelData() {

    }

    private lateinit var rootView: View

    private fun initRootView(view: View) {
        rootView = view
        toolBarBuilder = CommonToolBarBuilder(activity!!, common_toolbar)
        val contentLayoutId = getContentLayoutId()
        if (contentLayoutId > 0) {
            layoutInflater.inflate(contentLayoutId, baseContainer)
        }
        initView()
    }

    fun showLoadingDialog(content: String = "") {
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
    abstract fun initData(arguments: Bundle?)

    /**
     * 初始化监听
     */
    open fun initListener() {
    }


    fun hideErrorPage() {
        cl_error_page.visibility = View.GONE
    }

    fun showCorrectPage() {
        baseContainer.visibility = View.VISIBLE
        cl_error_page.visibility = View.GONE
    }


    override fun onStart() {
        super.onStart()
        Log.e(getLogTag(), "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(this.getLogTag(), "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(this.getLogTag(), "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(this.getLogTag(), "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(this.getLogTag(), "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(this.getLogTag(), "onDetach")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e(this.getLogTag(), "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(this.getLogTag(), "onCreate")
    }

    open fun getLogTag(): String {
        return this.javaClass.name
    }

}