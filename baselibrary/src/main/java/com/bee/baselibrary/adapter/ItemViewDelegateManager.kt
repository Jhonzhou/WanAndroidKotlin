package com.bee.baselibrary.adapter

import androidx.collection.SparseArrayCompat

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
internal class ItemViewDelegateManager<T> {
    private val delegates: SparseArrayCompat<ItemViewDelegate<T>> = SparseArrayCompat()
    fun addDelegate(delegate: ItemViewDelegate<T>?) {
        delegate ?: return
        delegates.put(delegates.size(), delegate)
    }

    fun getDelegateCount(): Int = delegates.size()

    fun getItemViewType(item: T, position: Int): Int {
        val size = delegates.size()
        for (index in 0 until delegates.size()) {
            if (delegates[index]!!.isForViewType(item, position)) {
                return index
            }
        }
        throw  IllegalArgumentException("No ItemViewDelegate added that matches position= $position in data source")
    }

    fun getItemViewDelegate(viewType: Int): ItemViewDelegate<T>? {
        return delegates.get(viewType)
    }

    fun convert(holder: BaseViewHolder, item: T, position: Int) {
        val itemViewType = getItemViewType(item, position)
        val delegate = delegates[itemViewType]
        delegate?.convert(holder, item, position)
    }

}