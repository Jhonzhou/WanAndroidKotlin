package com.bee.wanandroidkotlin.ui.ground.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.bee.baselibrary.utils.getRandomColor
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.http.beans.TagResponseBean
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class SystemTagDetailListAdapter(private val context: Context,
                                 dataList: List<TagResponseBean>?)
    : TagAdapter<TagResponseBean>(dataList) {
    override fun getView(parent: FlowLayout?, position: Int, t: TagResponseBean?): View {
        return (View.inflate(context, R.layout.item_text_tag, null) as TextView).apply {
            text = t?.name ?: ""
            val parseColor = try {
                Color.parseColor(getRandomColor())
            } catch (_: Exception) {
                @Suppress("DEPRECATION")
                context.resources.getColor(R.color.text_gray)
            }
            setTextColor(parseColor)
        }
    }

}