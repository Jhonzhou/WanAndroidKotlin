package com.bee.baselibrary.view.dialog.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author zhouxiaojun
 * @date 2018/9/25
 */
public abstract class BaseDialogFragment<T extends BaseDialogBuilder> extends DialogFragment {
    private static final String KEY_BUILDER = "key_builder";
    private View rootView;
    protected T builder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window win = getDialog().getWindow();
        if (win != null) {
            win.requestFeature(Window.FEATURE_NO_TITLE);
            win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        rootView = inflater.inflate(getLayoutId(), container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            builder = (T) bundle.getSerializable(KEY_BUILDER);
        }
        if (builder == null) {
            builder = (T) new BaseDialogBuilder();
        }
        initView(rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        if (win == null) {
            return;
        }
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams params = win.getAttributes();
        int gravity;
        switch (getGravityLocation()) {
            case TOP:
                gravity = Gravity.TOP;
                break;
            case CENTER:
                gravity = Gravity.CENTER;
                break;
            case BOTTOM:
                gravity = Gravity.BOTTOM;
                break;
            default:
                gravity = Gravity.CENTER;
                break;
        }
        params.gravity = gravity;

        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        int widthLayoutParams;
        switch (getWidthLayoutParams()) {
            case MATCH_PARENT:
                widthLayoutParams = ViewGroup.LayoutParams.MATCH_PARENT;
                break;
            case WRAP_CONTENT:
                widthLayoutParams = ViewGroup.LayoutParams.WRAP_CONTENT;
                break;
            default:
                widthLayoutParams = ViewGroup.LayoutParams.WRAP_CONTENT;
                break;
        }
        params.width = widthLayoutParams;

        int heightLayoutParams;
        switch (getHeightLayoutParams()) {
            case MATCH_PARENT:
                heightLayoutParams = ViewGroup.LayoutParams.MATCH_PARENT;
                break;
            case WRAP_CONTENT:
                heightLayoutParams = ViewGroup.LayoutParams.WRAP_CONTENT;
                break;
            default:
                heightLayoutParams = ViewGroup.LayoutParams.WRAP_CONTENT;
                break;
        }
        params.height = heightLayoutParams;

        win.setAttributes(params);
    }

    public void setBuilder(@NonNull T builder) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_BUILDER, builder);
        setArguments(bundle);
    }

    /**
     * dialog 的弹出位置 居中 置顶、底部
     *
     * @return 弹出位置
     */
    public DialogConstants.Location getGravityLocation() {
        if (builder != null && builder.getLocation() != null) {
            return builder.getLocation();
        }
        return DialogConstants.Location.CENTER;
    }

    /**
     * dialog 的宽度参数 包裹内容还是填充父窗体
     *
     * @return 宽度参数
     */
    public DialogConstants.DialogLayoutParams getWidthLayoutParams() {
        if (builder != null &&builder.getWidthLayoutParam() != null) {
            return builder.getWidthLayoutParam();
        }
        return DialogConstants.DialogLayoutParams.WRAP_CONTENT;
    }

    /**
     * dialog 的高度参数 包裹内容还是填充父窗体
     *
     * @return 高度参数
     */
    public DialogConstants.DialogLayoutParams getHeightLayoutParams() {
        if (builder != null &&builder.getHeightLayoutParam() != null) {
            return builder.getHeightLayoutParam();
        }
        return DialogConstants.DialogLayoutParams.WRAP_CONTENT;
    }

    public View getRootView() {
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

}
