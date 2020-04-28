package com.bee.baselibrary.view.dialog.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.bee.baselibrary.R;
import com.bee.baselibrary.view.dialog.base.BaseDialogBuilder;
import com.bee.baselibrary.view.dialog.base.BaseDialogFragment;


/**
 *
 */
public class HintDialogFragment extends BaseDialogFragment<HintDialogFragment.Builder> {

    @Override
    protected int getLayoutId() {
        return R.layout.base_ui_hint_dialog;
    }

    @Override
    protected void initView(View view) {
        String title = TextUtils.isEmpty(builder.title) ? "" : builder.title;
        String content = TextUtils.isEmpty(builder.content) ? "" : builder.content;
        String alterHint = TextUtils.isEmpty(builder.alterHint) ? "" : builder.alterHint;

        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvTitle.setVisibility(builder.showTitle ? View.VISIBLE : View.GONE);

        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(content);
        tvContent.setVisibility(builder.showContent ? View.VISIBLE : View.GONE);
        Button btnConfirm = view.findViewById(R.id.btn_confirm);
        btnConfirm.setText(alterHint);


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (builder.defaultCallBack) {
                    dismiss();
                } else {
                    if (builder.confirmCallBack != null) {
                        builder.confirmCallBack.confirm();
                    }
                }
            }
        });
    }

    public static class Builder extends BaseDialogBuilder<Builder> {
        private String title = "";
        private String content = "";
        private String alterHint = "";
        private ConfirmCallBack confirmCallBack;
        private boolean defaultCallBack = false;
        private boolean showTitle;
        private boolean showContent;

        public Builder setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
            return this;
        }

        public Builder setShowContent(boolean showContent) {
            this.showContent = showContent;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setAlterHint(String alterHint) {
            this.alterHint = alterHint;
            return this;
        }

        public Builder setConfirmCallBack(ConfirmCallBack confirmCallBack) {
            this.confirmCallBack = confirmCallBack;
            return this;
        }

        public Builder setDefaultCallBack(boolean defaultCallBack) {
            this.defaultCallBack = defaultCallBack;
            return this;
        }

        public DialogFragment create() {
            HintDialogFragment dialogFragment = new HintDialogFragment();
            dialogFragment.setBuilder(this);
            return dialogFragment;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", alterHint='" + alterHint + '\'' +
                    ", confirmCallBack=" + confirmCallBack +
                    ", defaultCallBack=" + defaultCallBack +
                    ", showTitle=" + showTitle +
                    ", showContent=" + showContent +
                    '}';
        }
    }

    public interface ConfirmCallBack {
        void confirm();
    }
}
