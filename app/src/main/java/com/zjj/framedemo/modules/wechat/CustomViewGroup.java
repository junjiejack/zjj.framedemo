package com.zjj.framedemo.modules.wechat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhoujunjie on 2018/4/18.
 * 1. 继承viewgroup 必须重写Onlayout方法
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
     * 重写onMeasure 方法
     * 绘制viewgroup的宽度和高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取子控件的宽高模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 获取子控件的宽高大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int count = getChildCount();
        if (count == 0) {   // 没有子控件时
            setMeasuredDimension(0, 0);
        }
        View child;
        MarginLayoutParams layoutParams;
        int width = 0;
        int height = 0;
        for (int i = 0 ; i < count;i++) {
            child = getChildAt(i);
            // 获取子控件的其它属性(外边距)
            layoutParams = (MarginLayoutParams) child.getLayoutParams();
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                // 子控件的宽高都是wrap_content的时候
                width += child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                height += child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                setMeasuredDimension(width, height * getChildCount());
            } else if (widthMode == MeasureSpec.AT_MOST) {
                width += child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
                setMeasuredDimension(width,heightSize);
            } else if (heightMode == MeasureSpec.AT_MOST) {
                height += child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                setMeasuredDimension(widthSize,height);
            }
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec);
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
        View child;
        for (int i = 0; i < getChildCount();i++) {
            // 获取每个子控件
            child = getChildAt(i);
            // 放置每个子控件的位置
            child.layout(0,height,child.getMeasuredWidth(),height+child.getMeasuredHeight());
            height += child.getMeasuredHeight();
        }
    }

    /** 覆写下列方法 以求返回MarginLayoutParams*/
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
