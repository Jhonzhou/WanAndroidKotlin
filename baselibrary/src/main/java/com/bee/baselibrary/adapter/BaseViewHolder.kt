package com.bee.baselibrary.adapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
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
class BaseViewHolder(private val mContext: Context, private val mConvertView: View) : RecyclerView.ViewHolder(mConvertView) {
    private val mViews: SparseArray<View> = SparseArray()

    companion object {
        fun createViewHolder(context: Context, itemView: View): BaseViewHolder {
            return BaseViewHolder(context, itemView)
        }

        fun createViewHolder(context: Context,
                             parent: ViewGroup, layoutId: Int): BaseViewHolder {
            val itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false)
            return BaseViewHolder(context, itemView)
        }
    }

    fun getConvertView(): View {
        return mConvertView
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(viewId: Int): T {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }
}