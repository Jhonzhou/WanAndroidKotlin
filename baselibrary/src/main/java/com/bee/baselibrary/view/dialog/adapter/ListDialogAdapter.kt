package com.bee.baselibrary.view.dialog.adapter

import android.text.TextUtils
import androidx.fragment.app.DialogFragment
import com.bee.baselibrary.R
import com.bee.baselibrary.adapter.BaseRvAdapter
import com.bee.baselibrary.adapter.BaseViewHolder
import com.bee.baselibrary.adapter.ItemViewDelegate
import com.bee.baselibrary.view.dialog.HintDialogOrPopItemBean


/**
 */
class ListDialogAdapter(dialogFragment: DialogFragment, dataList: List<HintDialogOrPopItemBean>) : BaseRvAdapter<HintDialogOrPopItemBean>() {

    init {
        addDelegate(object : ItemViewDelegate<HintDialogOrPopItemBean> {
            override fun getItemViewLayoutId(): Int {
                return R.layout.base_ui_item_list_dialog
            }

            override fun isForViewType(item: HintDialogOrPopItemBean, position: Int): Boolean {
                return true
            }

            override fun convert(holder: BaseViewHolder, item: HintDialogOrPopItemBean, position: Int) {
                holder.setVisible(R.id.v_divider, item.isShowDivider)
                        .setVisible(R.id.v_divider_line, item.isShowDividerLine)
                        .setText(R.id.tv_name, if (TextUtils.isEmpty(item.content)) "" else item.content!!)

                holder.getConvertView().setOnClickListener {
                    item.clickListener()
                    dialogFragment.dismiss()
                }
            }
        })
        setData(dataList)
    }

}