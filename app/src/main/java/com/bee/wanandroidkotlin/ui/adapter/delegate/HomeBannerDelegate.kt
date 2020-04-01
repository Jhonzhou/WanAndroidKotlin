package com.bee.wanandroidkotlin.ui.adapter.delegate

import android.widget.Button
import android.widget.ImageView
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
        val bannerTitle = holder.getView<Button>(R.id.tvBannerTitle)
        bannerTitle.text = "${item.title}我是副标题"
//        bannerTitle.visibility = View.VISIBLE
//        bannerTitle.gravity=Gravity.CENTER_VERTICAL
//        bannerTitle.setPadding(DensityUtils.dp2px(15F),0,DensityUtils.dp2px(15F),0)
        Glide.with(bannerImage).load(item.imagePath).into(bannerImage)

    }

}