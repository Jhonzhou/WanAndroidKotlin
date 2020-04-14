package com.bee.wanandroidkotlin.ui.home.adapter

import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.wanandroidkotlin.http.beans.TagResponseBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/14
 * @Description:
 */
class HotTagListAdapter() : BaseRvAdapter<TagResponseBean>() {
    init {
        addDelegate(object : ItemViewDelegate<TagResponseBean> {
            override fun getItemViewLayoutId(): Int {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun isForViewType(item: TagResponseBean, position: Int): Boolean = true

            override fun convert(holder: BaseViewHolder, item: TagResponseBean, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}