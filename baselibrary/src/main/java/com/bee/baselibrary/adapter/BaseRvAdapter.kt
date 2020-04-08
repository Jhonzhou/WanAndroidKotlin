package com.bee.baselibrary.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
open class BaseRvAdapter<T> : RecyclerView.Adapter<BaseViewHolder>() {
    protected val mDataList: ArrayList<T> = arrayListOf()
    private val delegateManager: ItemViewDelegateManager<T> = ItemViewDelegateManager()
    private var mOnItemClickListener: OnItemClickListener<T>? = null
    private var mOnItemLongClickListener: OnItemLongClickListener<T>? = null
    override fun getItemViewType(position: Int): Int {
        if (delegateManager.getDelegateCount() <= 0) {
            return super.getItemViewType(position)
        }
        return delegateManager.getItemViewType(mDataList[position], position)
    }

    fun addDelegate(delegate: ItemViewDelegate<T>?) {
        delegate ?: return
        delegateManager.addDelegate(delegate)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemViewDelegate = delegateManager.getItemViewDelegate(viewType)
                ?: return super.createViewHolder(parent, viewType)
        val baseViewHolder = BaseViewHolder.createViewHolder(parent.context, parent, itemViewDelegate.getItemViewLayoutId())
        onViewHolderCreated(baseViewHolder, baseViewHolder.getConvertView(), itemViewDelegate)
        mOnItemClickListener?.apply {
            baseViewHolder.getConvertView().setOnClickListener {
                val position = baseViewHolder.adapterPosition
                if (position == -1) {
                    return@setOnClickListener
                } else if (position >= mDataList.size) {
                    return@setOnClickListener
                }
                onItemClick(baseViewHolder, position, mDataList[position])
            }
        }
        mOnItemLongClickListener?.apply {
            baseViewHolder.getConvertView().setOnLongClickListener {
                val position = baseViewHolder.adapterPosition
                if (position == -1) {
                    return@setOnLongClickListener false
                } else if (position >= mDataList.size) {
                    return@setOnLongClickListener false
                }
                return@setOnLongClickListener onItemLongClick(baseViewHolder, position, mDataList[position])
            }
        }
        return baseViewHolder
    }

    override fun getItemCount(): Int = mDataList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegateManager.convert(holder, mDataList[position], position)
    }

    /**
     * 创建holder时回调
     */
    open fun onViewHolderCreated(holder: BaseViewHolder, itemView: View, itemViewDelegate: ItemViewDelegate<T>) {
    }

    fun addData(dataList: List<T>?) {
        dataList ?: return
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setData(dataList: List<T>?) {
        mDataList.clear()
        dataList?.let {
            mDataList.addAll(dataList)
        }
        notifyDataSetChanged()
    }

    fun addData(item: T?) {
        item ?: return
        mDataList.add(item)
        notifyItemChanged(mDataList.size - 1)
    }

    fun updateData(item: T?) {
        item ?: return
        val position = mDataList.indexOf(item)
        if (position != -1) {
            notifyItemChanged(position)
        }
    }

    fun removeData(item: T?) {
        item ?: return
        val position = mDataList.indexOf(item)
        if (position != -1) {
            notifyItemRemoved(position)
        }
    }

    fun setOnItemLongClickListener(clickListener: OnItemLongClickListener<T>) {
        mOnItemLongClickListener = clickListener
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener<T>) {
        mOnItemClickListener = clickListener
    }

    interface OnItemLongClickListener<T> {
        fun onItemLongClick(baseViewHolder: BaseViewHolder, position: Int, item: T): Boolean
    }

    interface OnItemClickListener<T> {
        fun onItemClick(baseViewHolder: BaseViewHolder, position: Int, item: T)
    }
}
