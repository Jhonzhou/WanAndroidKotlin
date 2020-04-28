package com.bee.baselibrary.view.dialog


/**
 * @author zhouxiaojun
 * @date 2018/9/25
 */
class HintDialogOrPopItemBean() {
    //内容
    var content: String? = null
        private set
    //是否显示分割线
    var isShowDividerLine = true
    //是否显示条目间距
    var isShowDivider = false
        private set
    var clickListener: () -> Unit = {}
        private set

    constructor(content: String,
                showDividerLine: Boolean = false,
                showDivider: Boolean = false,
                clickListener: () -> Unit = {}) : this() {
        this.content = content
        this.isShowDividerLine = showDividerLine
        this.isShowDivider = showDivider
        this.clickListener = clickListener
    }
}
