package com.bee.baselibrary.view.dialog.ui;


import android.view.View;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bee.baselibrary.R;
import com.bee.baselibrary.view.dialog.HintDialogOrPopItemBean;
import com.bee.baselibrary.view.dialog.adapter.ListDialogAdapter;
import com.bee.baselibrary.view.dialog.base.BaseDialogBuilder;
import com.bee.baselibrary.view.dialog.base.BaseDialogFragment;
import com.bee.baselibrary.view.dialog.base.DialogConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部弹出框 （dialog实现）
 */
public class BottomListDialogFragment extends BaseDialogFragment<BottomListDialogFragment.Builder> {

    @Override
    protected int getLayoutId() {
        return R.layout.base_ui_pop_window_bottom_list;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        ListDialogAdapter adapter = new ListDialogAdapter(this, builder.mDataList);
        recyclerView.setAdapter(adapter);
    }


    public static class Builder extends BaseDialogBuilder<Builder> {
        private List<HintDialogOrPopItemBean> mDataList;

        public Builder addItem(HintDialogOrPopItemBean bean) {
            if (bean != null) {
                if (mDataList == null) {
                    mDataList = new ArrayList<>();
                }
                mDataList.add(bean);
            }
            return this;
        }


        public DialogFragment create() {
            BottomListDialogFragment dialogFragment = new BottomListDialogFragment();
            dialogFragment.setBuilder(this);
            return dialogFragment;
        }
    }

    @Override
    public DialogConstants.Location getGravityLocation() {
        return DialogConstants.Location.BOTTOM;
    }

    @Override
    public DialogConstants.DialogLayoutParams getWidthLayoutParams() {
        return DialogConstants.DialogLayoutParams.MATCH_PARENT;
    }


}
