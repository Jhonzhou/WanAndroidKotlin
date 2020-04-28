package com.bee.baselibrary.view.dialog.base;

import java.io.Serializable;

/**
 * @author zhouxiaojun
 * @date 2018/9/27
 */
public class BaseDialogBuilder<T extends BaseDialogBuilder> implements Serializable {
    private DialogConstants.Location location;
    private DialogConstants.DialogLayoutParams widthLayoutParam;
    private DialogConstants.DialogLayoutParams heightLayoutParam;

    public DialogConstants.Location getLocation() {
        return location;
    }

    public T setLocation(DialogConstants.Location location) {
        this.location = location;
        return (T) this;
    }

    public DialogConstants.DialogLayoutParams getWidthLayoutParam() {
        return widthLayoutParam;
    }

    public T setWidthLayoutParam(DialogConstants.DialogLayoutParams widthLayoutParam) {
        this.widthLayoutParam = widthLayoutParam;
        return (T) this;
    }

    public DialogConstants.DialogLayoutParams getHeightLayoutParam() {
        return heightLayoutParam;
    }

    public T setHeightLayoutParam(DialogConstants.DialogLayoutParams heightLayoutParam) {
        this.heightLayoutParam = heightLayoutParam;
        return (T) this;
    }
}
