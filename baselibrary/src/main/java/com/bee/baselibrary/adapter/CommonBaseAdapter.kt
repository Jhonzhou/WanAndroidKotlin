package com.bee.baselibrary.adapter


/**
 *
 *
 * @author: JhonZhou
 * @date:  2020/3/31
 * @Description:
 */
class CommonBaseAdapter<T> :BaseRvAdapter<T>(){
    fun setDataList(dataList: ArrayList<T>?) {
        mDataList.clear()
        dataList?.let {
            mDataList.addAll(it)
        }

    }
}
