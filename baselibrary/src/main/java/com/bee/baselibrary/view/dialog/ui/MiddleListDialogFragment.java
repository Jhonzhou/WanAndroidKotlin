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
 * 中部dialog
 */
public class MiddleListDialogFragment extends BaseDialogFragment<MiddleListDialogFragment.Builder> {

    @Override
    protected int getLayoutId() {
        return R.layout.base_ui_list_dialog;
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        ListDialogAdapter adapter = new ListDialogAdapter(this, builder.mDataList);
        recyclerView.setAdapter(adapter);
    }


    public static class Builder extends BaseDialogBuilder<Builder> {
        List<HintDialogOrPopItemBean> mDataList;

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
            MiddleListDialogFragment dialogFragment = new MiddleListDialogFragment();
            dialogFragment.setBuilder(this);
            return dialogFragment;
        }
    }

    @Override
    public DialogConstants.DialogLayoutParams getWidthLayoutParams() {
        return DialogConstants.DialogLayoutParams.MATCH_PARENT;
    }

}
