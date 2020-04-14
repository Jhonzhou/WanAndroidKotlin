package com.bee.wanandroidkotlin.ui.me.adapter

import android.view.View
import android.widget.TextView
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.beans.HomeMeItemBean

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/13
 * @Description:
 */
class HomeMeAdapter : BaseRvAdapter<HomeMeItemBean>() {
    init {
        addDelegate(object : ItemViewDelegate<HomeMeItemBean> {
            override fun getItemViewLayoutId(): Int = R.layout.item_home_me
            override fun isForViewType(item: HomeMeItemBean, position: Int): Boolean = true
            override fun convert(holder: BaseViewHolder, item: HomeMeItemBean, position: Int) {
                val vTag = holder.getView<View>(R.id.vTag)
                val tvTitle = holder.getView<TextView>(R.id.tvTitle)
                val tvRight = holder.getView<TextView>(R.id.tvRight)
                val vDivider = holder.getView<View>(R.id.vDivider)
                vTag.visibility = if (item.isShowTag) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                tvTitle.text = item.title
                tvRight.text = item.rightText
                vDivider.visibility = if (item.isShowDivider) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                holder.getConvertView().setOnClickListener {
                    item.itemClickListener?.apply {
                        this()
                    }
                }
            }

        })
    }
}