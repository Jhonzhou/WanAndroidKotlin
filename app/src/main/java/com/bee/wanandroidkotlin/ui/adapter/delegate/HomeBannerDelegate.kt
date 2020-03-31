package com.bee.wanandroidkotlin.ui.adapter.delegate

import android.widget.ImageView
import android.widget.TextView
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.wanandroidkotlin.R
import com.bee.wanandroidkotlin.http.beans.HomeBannerResponse
import com.bumptech.glide.Glide

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
class HomeBannerDelegate : ItemViewDelegate<HomeBannerResponse> {

    override fun getItemViewLayoutId(): Int = R.layout.item_home_first_banner

    override fun isForViewType(item: HomeBannerResponse, position: Int): Boolean = true

    override fun convert(holder: BaseViewHolder, item: HomeBannerResponse, position: Int) {
        val bannerImage = holder.getView<ImageView>(R.id.bannerImage)
        val bannerTitle = holder.getView<TextView>(R.id.bannerTitle)

        Glide.with(bannerImage).load(item.imagePath).into(bannerImage)
        bannerTitle.text = item.title

    }

}