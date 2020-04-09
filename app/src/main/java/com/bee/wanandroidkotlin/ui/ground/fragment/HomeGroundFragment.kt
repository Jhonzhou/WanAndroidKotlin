package com.bee.wanandroidkotlin.ui.ground.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bee.baselibrary.base.BaseFragment
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.ui.project.fragment.TencentTagFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home_project.*

/**
 *
 * 首页广场Fragment
 * @author: JhonZhou
 * @date:  2020/3/30
 * @Description:
 */
class HomeGroundFragment : BaseFragment() {
    override fun getContentLayoutId(): Int = R.layout.fragment_home_ground
    private val mAdapter: FragmentStateAdapter by lazy {
        object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> {
                        SystemTabFragment()
                    }
                    1 -> {
                        NavigationTabFragment()
                    }
                    2 -> {
                        TencentTagFragment()
                    }
                    else -> {
                        throw IllegalArgumentException("current position $position not support")
                    }
                }
            }

        }
    }

    override fun initView() {
        toolBarBuilder.hideCommonBaseTitle()
        vpContent.adapter = mAdapter
        TabLayoutMediator(tlTitle, vpContent,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.text = when (position) {
                        0 -> {
                            "体系"
                        }
                        1 -> {
                            "导航"
                        }
                        2 -> {
                            "公众号"
                        }
                        else -> {
                            throw IllegalArgumentException("current position $position not support")
                        }
                    }
                }).attach()
    }

    override fun initData(arguments: Bundle?) {

    }

}