package com.bee.baselibrary.adapter

import androidx.annotation.LayoutRes

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
interface ItemViewDelegate<T> {
    @LayoutRes
    fun getItemViewLayoutId(): Int

    fun isForViewType(item: T, position: Int): Boolean

    fun convert(holder: BaseViewHolder, item: T, position: Int)
}