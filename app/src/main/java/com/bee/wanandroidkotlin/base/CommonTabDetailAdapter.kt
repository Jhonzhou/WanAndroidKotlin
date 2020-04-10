package com.bee.wanandroidkotlin.base

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.beans.BaseTagTitleBean
import com.bee.wanandroidkotlin.utils.getRandomColor
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/9
 * @Description:
 */
class CommonTabDetailAdapter<T:BaseTagTitleBean>(private val context: Context,
                             dataList: List<T>?)
    : TagAdapter<T>(dataList) {
    override fun getView(parent: FlowLayout?, position: Int, t: T?): View {
        return (View.inflate(context, R.layout.item_text_tag, null) as TextView).apply {
            text = t?.getTitleName() ?: ""
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