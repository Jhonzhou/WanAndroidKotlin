package com.bee.wanandroidkotlin.ui.adapter

import android.widget.TextView
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.zhy.view.flowlayout.TagFlowLayout

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class SystemTagListAdapter : BaseRvAdapter<TagResponseBean>() {
    private var mTagClickListener: TagClickListener? = null

    init {
        addDelegate(object : ItemViewDelegate<TagResponseBean> {
            override fun getItemViewLayoutId(): Int = R.layout.item_system_tag_list

            override fun isForViewType(item: TagResponseBean, position: Int): Boolean = true

            override fun convert(holder: BaseViewHolder, item: TagResponseBean, position: Int) {
                val tvTitle = holder.getView<TextView>(R.id.tv_title)
                tvTitle.text = item.name ?: ""


                val tflContent = holder.getView<TagFlowLayout>(R.id.tflContent)
                val mAdapter = SystemTagDetailListAdapter(
                        holder.getConvertView().context,
                        item.children!!)
                mTagClickListener?.let {
                    tflContent.setOnTagClickListener { view, position, parent ->
                        if (position >= item.children.size) {
                            return@setOnTagClickListener false
                        }
                        it.onClick(item.children[position])
                        true
                    }
                }

                tflContent.adapter = mAdapter
                mAdapter.notifyDataChanged()
            }
        })
    }

    fun setTagClickListener(clickListener: TagClickListener) {
        mTagClickListener = clickListener
    }

    interface TagClickListener {
        fun onClick(item: TagResponseBean)
    }
}
