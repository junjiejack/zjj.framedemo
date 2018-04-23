package com.zjj.framedemo.modules.wechat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by zhoujunjie on 2018/4/18.
 * 1. 继承viewgroup 必须重写Onlayout方法(不用像继承view去实现ondraw的方法)
 * 2. 实现构造方法
 * 3. 自定义控件中两个重要的方法 onMeasure
 */

public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * @param changed  给子控件布局
     * @param l       left 左边
     * @param t       top 顶部
     * @param r       right 右边
     * @param b       bottom 底部
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = 0;
        // 获取子控件的总高度
        for (int i = 0; i < getChildCount();i++) {
            int childHeight = getChildAt(i).getMeasuredHeight();
            height += childHeight;
        }
    }
}
