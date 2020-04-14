package com.bee.wanandroidkotlin.ui.common.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import com.bee.baselibrary.base.BaseActivity
import com.bee.wanandroidkotlin.utils.ToastAlone
import kotlinx.android.synthetic.main.activity_detail_content_web.*


/**
 *
 *  文章详情界面
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
private const val KEY_CONTENT_URL = "key_content_url"
private const val KEY_TITLE = "key_title"
private const val TAG = "WebActivity"

class DetailContentWebActivity : BaseActivity() {
    companion object {
        fun startActivity(activity: Activity, contentUrl: String, title: String? = null) {
            val intent = Intent(activity, DetailContentWebActivity::class.java)
            intent.putExtra(KEY_CONTENT_URL, contentUrl)
            title?.apply {
                intent.putExtra(KEY_TITLE, this)
            }
            activity.startActivity(intent)
        }

        fun startFragment(fragment: Fragment, contentUrl: String, title: String? = null) {
            val intent = Intent(fragment.context, DetailContentWebActivity::class.java)
            intent.putExtra(KEY_CONTENT_URL, contentUrl)
            title?.apply {
                intent.putExtra(KEY_TITLE, this)
            }

            fragment.startActivity(intent)
        }
    }

    private lateinit var mContentUrl: String
    private lateinit var mTitle: String
    override fun getContentLayoutId(): Int = com.bee.wanandroidkotlin.R.layout.activity_detail_content_web

    override fun initView() {
        initWebView()
    }

    override fun onBackPressed() {
        if (wvDetail.canGoBack()) {
            wvDetail.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun initData(intent: Intent?) {
        if (intent == null) {
            finish()
            ToastAlone.showToast("参数不能为空")
            return
        }
        val contentUrl = intent.getStringExtra(KEY_CONTENT_URL)
        if (TextUtils.isEmpty(contentUrl)) {
            finish()
            ToastAlone.showToast("地址不能为空")
            return
        }
        mContentUrl = contentUrl!!
        mTitle = intent.getStringExtra(KEY_TITLE) ?: ""
        if (TextUtils.isEmpty(mTitle)) {
            toolBarBuilder.hideCommonBaseTitle()
        } else {
            toolBarBuilder.setTitle(mTitle)
        }

        wvDetail.loadUrl(mContentUrl)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        wvDetail.clearCache(true)

        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        wvDetail.clearHistory()
        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        wvDetail.clearFormData()


        initWebSettings()


        initWebClient()
    }

    private fun initWebClient() {
        wvDetail.webViewClient = object : WebViewClient() {
            //作用：开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pbLoading.visibility = View.VISIBLE
                vDivider.visibility = View.GONE
            }

            //作用：在页面加载结束时调用。我们可以关闭loading 条，切换程序动作。
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pbLoading.visibility = View.GONE
                vDivider.visibility = View.VISIBLE
            }

            //步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        wvDetail.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                //进度条更新
                super.onProgressChanged(view, newProgress)
                pbLoading.progress = newProgress
            }

            override fun onPermissionRequest(request: PermissionRequest?) {
                super.onPermissionRequest(request)
                //请求权限
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                //更新标题
                toolBarBuilder.setTitle(title ?: mTitle)
                Log.e(TAG,"onReceivedTitle:$title")
            }

            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                //支持javascript的警告框
                if (isFinishing) {
                    return false
                }
                val b2 = AlertDialog.Builder(this@DetailContentWebActivity).setMessage(message)
                        .setPositiveButton("确定") { dialog, which ->
                            result?.confirm()
                        }
                b2.setCancelable(false)
                b2.create()
                b2.show()
                return true
            }

            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                //支持javascript的确认框
                if (isFinishing) {
                    return false
                }
                val b2 = AlertDialog.Builder(this@DetailContentWebActivity).setMessage(message)
                        .setPositiveButton("确定") { dialog, which ->
                            result?.confirm()
                        }
                        .setNegativeButton("取消") { dialog, which ->
                            result?.cancel()
                        }
                b2.setCancelable(false)
                b2.create()
                b2.show()
                return true
            }
        }
    }

    private fun initWebSettings() {
        //声明WebSettings子类
        val webSettings = wvDetail.settings

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可


        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件

        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存


        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"//设置编码格式
    }

    override fun onDestroy() {
        if (wvDetail != null) {
            wvDetail.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            wvDetail.clearHistory()

            (wvDetail.parent as ViewGroup).removeView(wvDetail)
            wvDetail.destroy()
        }
        super.onDestroy()
    }


}