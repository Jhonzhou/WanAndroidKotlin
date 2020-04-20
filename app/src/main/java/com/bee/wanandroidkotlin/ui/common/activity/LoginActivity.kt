package com.bee.wanandroidkotlin.ui.common.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.WanAndroidModel
import com.bee.wanandroidkotlin.ui.common.MainActivity
import com.bee.wanandroidkotlin.utils.ToastAlone
import com.bee.wanandroidkotlin.utils.launchMain
import kotlinx.android.synthetic.main.activity_login.*

/**
 *
 *  登录界面
 * @author: JhonZhou
 * @date:  2020/3/20
 * @Description:
 */
private const val KEY_NEED_BACK = "KEY_NEED_BACK"
private const val REQUEST_CODE_REGIST = 101

class LoginActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun startForResult(fragment: Fragment, requestCode: Int) {
            val intent = Intent(fragment.context, LoginActivity::class.java)
            intent.putExtra(KEY_NEED_BACK, true)
            fragment.startActivityForResult(intent, requestCode)
        }

        fun startForResult(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.putExtra(KEY_NEED_BACK, true)
            activity.startActivityForResult(intent, requestCode)
        }

        fun start(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var isNeedBack = false

    override fun getContentLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        toolBarBuilder.setTitle("登录")
        toolBarBuilder.hideBackIcon()
        btnLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
    }

    override fun initData(intent: Intent?) {
        intent?.apply {
            isNeedBack = getBooleanExtra(KEY_NEED_BACK, false)
        }
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.btnLogin -> {
                doLogin()

            }
            R.id.tvRegister -> {
                startActivityForResult(Intent(this, RegisterActivity::class.java), REQUEST_CODE_REGIST)
            }
            else -> {
                return
            }
        }
    }

    private fun doLogin() {
        val name = etName.text
        if (name == null || TextUtils.isEmpty(name.toString())) {
            ToastAlone.showToast("用户名不能为空")
            return
        }
        val password = etPassword.text
        if (password == null || TextUtils.isEmpty(password.toString())) {
            ToastAlone.showToast("密码不能为空")
            return
        }
        launchMain {
            showLoadingDialog()
            val loginResult = WanAndroidModel().login(name.toString(), password.toString())
            loginResult.handlerResult {
                loginSuccess()
            }
            hideLoadingDialog()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_REGIST) {
            if (resultCode == Activity.RESULT_OK) {
                loginSuccess()
            }
        }
    }

    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    private fun loginSuccess() {
        var isLogin by Preference(Constants.SP.SP_LOGIN, false)
        isLogin = true
        ToastAlone.showToast("登录成功")
        if (isNeedBack) {
            setResult(Activity.RESULT_OK)
        } else {
            startActivity(Intent(getMActivity(), MainActivity::class.java))
        }
        finish()
    }
}