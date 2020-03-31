package com.bee.wanandroidkotlin.ui.activity

import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bee.baselibrary.base.BaseActivity
import com.bee.baselibrary.utils.Preference
import com.bee.baselibrary.utils.launchMain
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.WanAndroidModel
import com.bee.wanandroidkotlin.utils.ToastAlone
import kotlinx.android.synthetic.main.activity_login.*

/**
 *
 *  登录界面
 * @author: JhonZhou
 * @date:  2020/3/20
 * @Description:
 */
class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun getContentLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        toolBarBuilder.setTitle("登录")
        toolBarBuilder.hideBackIcon()
        btnLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
    }

    override fun initData(intent: Intent?) {
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.btnLogin -> {
                doLogin()

            }
            R.id.tvRegister -> {
                startActivity(Intent(this, RegisterActivity::class.java))
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
        launchMain{
            showLoading()
            val loginResult = WanAndroidModel().login(name.toString(), password.toString())
            loginResult.handlerResult {
                startActivity(Intent(getMActivity(), MainActivity::class.java))
                var isLogin by Preference(Constants.SP.SP_LOGIN, false)
                isLogin = true
                ToastAlone.showToast("登录成功")
                finish()
            }
            hideLoadingDialog()
        }
    }

}