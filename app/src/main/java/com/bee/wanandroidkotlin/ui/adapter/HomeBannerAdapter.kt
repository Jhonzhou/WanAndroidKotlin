package com.bee.wanandroidkotlin.ui.adapter

import android.content.Context
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.wanandroidkotlin.http.beans.HomeBannerResponse
import com.bee.wanandroidkotlin.ui.adapter.delegate.HomeBannerDelegate

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
class HomeBannerAdapter(context: Context) : BaseRvAdapter<HomeBannerResponse>() {
    init {
        addDelegate(HomeBannerDelegate())
    }
}