package com.bee.wanandroidkotlin.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bee.wanandroidkotlin.constants.Constants
import com.bee.wanandroidkotlin.http.beans.ProjectTabResponseBean
import com.bee.wanandroidkotlin.ui.fragment.ProjectDetailListFragment

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/8
 * @Description:
 */
class HomeProjectTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val mDataList = arrayListOf<ProjectTabResponseBean>()
    override fun getItemCount(): Int = mDataList.size

    override fun createFragment(position: Int): Fragment {
        val fragment = ProjectDetailListFragment()
        val bundle = Bundle()
        bundle.putSerializable(Constants.KEY_DATA, mDataList[position])
        fragment.arguments = bundle
        return fragment
    }

    override fun containsItem(itemId: Long): Boolean {
        return super.containsItem(itemId)

    }

    override fun getItemId(position: Int): Long {
        return mDataList[position].id.toLong()
    }

    fun getItem(position: Int): ProjectTabResponseBean? {
        return if (mDataList.size > position) {
            mDataList[position]
        } else {
            null
        }
    }

    fun setData(resultList: List<ProjectTabResponseBean>?) {
        mDataList.clear()
        resultList?.let {
            mDataList.addAll(it)
        }
        notifyDataSetChanged()
    }

}