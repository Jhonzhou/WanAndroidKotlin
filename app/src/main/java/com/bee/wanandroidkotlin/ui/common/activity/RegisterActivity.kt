package com.bee.wanandroidkotlin.ui.common.activity

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.bee.baselibrary.base.BaseActivity
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.http.WanAndroidModel
import com.bee.wanandroidkotlin.utils.ToastAlone
import com.bee.wanandroidkotlin.utils.launchMain
import kotlinx.android.synthetic.main.activity_login.etName
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_register.*

/**
 *
 * 注册界面
 * @author: JhonZhou
 * @date:  2020/3/23
 * @Description:
 */
class RegisterActivity : BaseActivity(), View.OnClickListener {


    override fun getContentLayoutId(): Int = R.layout.activity_register

    override fun initView() {

    }

    override fun initListener() {
        super.initListener()
        btnLogin.setOnClickListener(this)
    }

    override fun initData(intent: Intent?) {
        toolBarBuilder.setTitle("注册")
    }

    override fun onClick(v: View?) {
        v ?: return
        when (v.id) {
            R.id.btnLogin -> {
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
                val rePassword = etRePassword.text
                if (rePassword == null || TextUtils.isEmpty(rePassword.toString())) {
                    ToastAlone.showToast("确认密码不能为空")
                    return
                }
                if (!TextUtils.equals(password, rePassword)) {
                    ToastAlone.showToast("两次密码不一致")
                    return
                }
                launchMain {
                    showLoadingDialog()
                    val responseResult = WanAndroidModel().register(name.toString(), password.toString(), rePassword.toString())
                    responseResult.handlerResult {
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                    hideLoadingDialog()
                }
            }
        }
    }
}