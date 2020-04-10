package com.bee.wanandroidkotlin.ui.ground.adapter

import android.widget.TextView
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.base.CommonTabDetailAdapter
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.bee.wanandroidkotlin.listener.TagClickListener
import com.zhy.view.flowlayout.TagFlowLayout

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class SystemTagListAdapter : BaseRvAdapter<TagResponseBean>() {
    private var mTagClickListener: TagClickListener<TagResponseBean>? = null

    init {
        addDelegate(object : ItemViewDelegate<TagResponseBean> {
            override fun getItemViewLayoutId(): Int = R.layout.item_tag_title_list

            override fun isForViewType(item: TagResponseBean, position: Int): Boolean = true

            override fun convert(holder: BaseViewHolder, item: TagResponseBean, position: Int) {
                val tvTitle = holder.getView<TextView>(R.id.tv_title)
                tvTitle.text = item.name ?: ""

                val tflContent = holder.getView<TagFlowLayout>(R.id.tflContent)
                val mAdapter = CommonTabDetailAdapter(
                        holder.getConvertView().context,
                        item.children!!)
                mTagClickListener?.let {
                    tflContent.setOnTagClickListener { _, position, _ ->
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

    fun setTagClickListener(clickListener: TagClickListener<TagResponseBean>) {
        mTagClickListener = clickListener
    }

}
