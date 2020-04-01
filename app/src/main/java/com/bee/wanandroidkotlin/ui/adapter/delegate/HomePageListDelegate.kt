package com.bee.wanandroidkotlin.ui.adapter.delegate

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.http.beans.HomePageListResponseData

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/4/1
 * @Description:
 */
class HomePageListDelegate : ItemViewDelegate<HomePageListResponseData> {
    override fun getItemViewLayoutId(): Int = R.layout.item_home_first_page

    override fun isForViewType(item: HomePageListResponseData, position: Int): Boolean = true

    override fun convert(holder: BaseViewHolder, item: HomePageListResponseData, position: Int) {
        val tvUserName = holder.getView<TextView>(R.id.tvUserName)
        val tvTime = holder.getView<TextView>(R.id.tvTime)
        val tvTitle = holder.getView<TextView>(R.id.tvTitle)
        val tvTop = holder.getView<TextView>(R.id.tvTop)
        val tvSuperChapterName = holder.getView<TextView>(R.id.tvSuperChapterName)
        val ivCollect = holder.getView<ImageView>(R.id.ivCollect)
        var userName = ""
        if (TextUtils.isEmpty(item.author)) {
            userName = item.author!!
        } else {
            if (TextUtils.isEmpty(item.shareUser)) {
                userName = item.shareUser!!
            }
        }
        tvUserName.text = userName
        tvTime.text = item.niceDate ?: ""
        tvTitle.text = item.title ?: ""
        tvTop.visibility = if (item.type == 0) {
            View.GONE
        } else {
            View.VISIBLE
        }
        tvSuperChapterName.text = item.superChapterName ?: ""
        ivCollect.isSelected = item.collect

    }

}