package com.zjj.framedemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.zjj.framedemo.R;



/**
 * @author zhoujunjie on 2017/12/27.
 */

public class CustomTagLayout extends View implements View.OnClickListener{

    private Paint mPaint;
    private String text;
    private int textColor;
    private int textSize;
    private Rect rect;
    private Context context;
    private int backgroud;

    /**
     * 1.重写构造方法
     */
    public CustomTagLayout(Context context) {
        this(context, null);
    }

    public CustomTagLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTagLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        /** 2.获取自定义的属性 */
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTagLayout, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTagLayout_text:
                    text = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTagLayout_textColor:
                    textColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTagLayout_textSize:
                    textSize = typedArray.getDimensionPixelOffset(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomTagLayout_textBackground:
                    backgroud = typedArray.getColor(attr,Color.RED);
                    break;
            }
            /** 绘制文本*/
            mPaint = new Paint();
            mPaint.setTextSize(textSize);
            mPaint.setColor(textColor);

            rect = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), rect);
            this.setOnClickListener(this);
        }
        /** 记住要回收 */
        typedArray.recycle();

    }

    /**
     * 3.重写onMeasure方法
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasured = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasured = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthMeasured;
        } else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(text, 0, text.length(), rect);
            float textWidth = rect.width();
            int actualWidth = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width = actualWidth;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightMeasured;
        } else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(text, 0, text.length(), rect);
            float textHeight = rect.height();
            int actualHeight = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            height = actualHeight;
        }
        setMeasuredDimension(width, height);
    }

    /**
     * 4.重写onDraw方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        int radius = 15;
        mPaint.setStrokeWidth(2);
        mPaint.setAntiAlias(true);
        RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(rectF,radius,radius,mPaint);

        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.FILL);
        /**----- 绘制文本的距离(居中) ----*/
        canvas.drawText(text, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, mPaint);
        System.out.println("width:" + getWidth() + ",height:" + getHeight() + ",rect width:" + rect.width() + ",height:" + rect.height());

    }

    @Override
    public void onClick(View v) {
        int color = context.getResources().getColor(R.color.blue_mine);
        if (textColor == color) {
            textColor = Color.BLACK;
            backgroud = Color.RED;
        } else {
            textColor = color;
            backgroud = context.getResources().getColor(R.color.blue_mine);
        }
        mPaint.setColor(textColor);
        postInvalidate();
    }
}
