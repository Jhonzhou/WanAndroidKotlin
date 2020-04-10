package com.bee.wanandroidkotlin.ui.ground.activity

import android.content.Intent
import android.os.Bundle
import com.bee.baselibrary.base.BaseActivity
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.ui.ground.fragment.TabDetailFragment

/**
 *
 * 根据TagResponseBean 获取对应类别activity
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class TagDetailListActivity : BaseActivity() {

    override fun getContentLayoutId(): Int = 0

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
    }

    override fun initData(intent: Intent?) {
        if (intent == null) {
            finish()
            return
        }
        val serializable = intent.getSerializableExtra(Constants.KEY_DATA)
        serializable ?: finish()
        if (serializable == null) {
            finish()
            return
        }
        val fragment = TabDetailFragment()
        val bundle = Bundle()
        bundle.putSerializable(Constants.KEY_DATA, serializable)
        fragment.arguments = bundle
        displayFragment(fragment)
    }

}